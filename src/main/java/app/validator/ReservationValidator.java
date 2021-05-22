package app.validator;

import app.dao.*;
import app.model.*;
import app.model.generic.Place;
import app.validator.generic.FieldErrors;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationValidator extends Validator<Reservation> {
    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired ReservationDao reservationDao;
    @Autowired AreaDao areaDao;
    @Autowired ZoneDao zoneDao;
    @Autowired ReservationZoneDao reservationZoneDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg != null) return forbidden();  // Importante: reservas de una parte
        return ifPerson(session, Citizen.class);
    }

    @Override
    public boolean add(HttpSession session, Integer arg) {
        if (arg == null || areaPeriodDao.getById(arg) == null) return forbidden();
        return ifPerson(session, Citizen.class);
    }

    @Override
    public boolean update(HttpSession session, Integer arg) {
        if (arg == null || reservationDao.getById(arg) == null) return forbidden();
        return ifPerson(session);
    }

    @Override
    public boolean delete(HttpSession session, Integer arg) {
        if (arg == null) return forbidden();
        return update(session, arg);
    }

    @Override
    public void object(Reservation r, FieldErrors errors) {
        List<Zone> zones = r.getZones().stream().map(zoneDao::getById).collect(Collectors.toList());
        AreaPeriod areaPeriod = areaPeriodDao.getParentOf(r);
        Area area = areaDao.getParentOf(areaPeriod);
        LocalDate today = LocalDate.now();
        LocalDate date = r.getDate();

        if (date == null) {
            errors.accept("date", "Formato de fecha invalido");
        } else if (date.isBefore(areaPeriod.scheduleStart) || date.isAfter(areaPeriod.scheduleEnd)) {
            errors.accept("date", "Fecha selecionada fuera del horario reservado");
        } else if (date.isBefore(today) || date.isAfter(today.plusDays(2))) {
            errors.accept("date", "Solo se puede reservar como maximo con dos dias de antelacion");
        }

        boolean zonesExist = zones.stream().allMatch(z -> z != null && z.getArea() == area.getId());

        if (zonesExist) {
            if (r.getOccupied() < 1) {
                errors.accept("occupied", "Mínimo ha de haber 1 persona");
            } else {
                int capacity = zones.stream().mapToInt(Zone::getCapacity).sum();
                if (r.getOccupied() > capacity)
                    errors.accept("occupied", "Demasiadas personas para la capacidad de las zonas selecionadas");
            }
            // Comprobar que, para toda reserva que estás haciendo, no existra otra en:
            // - en el área que estás reservando,
            // - en el día que estás reservando
            // - y en el horario que estás reservando
            Set<Reservation> reservations = new HashSet<>(this.reservationDao.getByAreaPeriod(r.getAreaPeriod()));
            Set<Integer> idReservedZones = new HashSet<>();
            for (Reservation reservation : reservations) {
                if (!reservation.getDate().equals(r.getDate())) reservations.remove(reservation);
                else
                    for (ReservationZone reservationZone : this.reservationZoneDao.getChildsOf(reservation))
                        idReservedZones.add(reservationZone.getZone());
            }
            Set<Integer> setIdZones = new HashSet<>(r.getZones());
            // idReservedZones ==> intersección entre las zonas reservadas de cualquier reserva (menos r) y las zonas reservadas en r
            idReservedZones.retainAll(setIdZones);
            if (! idReservedZones.isEmpty()){
                String stringError = idReservedZones.stream().map(zoneDao::getById).map(Zone::getName).collect(Collectors.joining(", "));
                errors.accept("zones", "Las siguientes zonas están reservadas: " + stringError);
            }

        } else {
            errors.accept("zone", "Alguna zona selecionada no esta dentro del area reservada");
        }
    }
}
