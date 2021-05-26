package app.validator;

import app.dao.*;
import app.model.*;
import app.model.generic.Activeable;
import app.model.generic.Person;
import app.validator.generic.FieldErrors;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationValidator extends Validator<Reservation> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired ReservationDao reservationDao;
    @Autowired AreaDao areaDao;
    @Autowired ZoneDao zoneDao;
    @Autowired ReservationZoneDao reservationZoneDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg == null) return ifPerson(session, Citizen.class, ControlStaff.class);
        AreaPeriod areaPeriod = areaPeriodDao.getById(arg);
        if (!Activeable.isActive(areaPeriod)) return forbidden();
        Area area = areaDao.getParentOf(areaPeriod);
        if (!area.isActive()) return forbidden();
        Municipality municipality = municipalityDao.getParentOf(area);
        if (!municipality.isActive()) return forbidden();
        return ifPerson(session, MunicipalManager.class);
    }

    @Override
    public boolean add(HttpSession session, Integer arg) {
        AreaPeriod areaPeriod = areaPeriodDao.getById(arg);
        if (!Activeable.isActive(areaPeriod)) return forbidden();
        Area area = areaDao.getParentOf(areaPeriod);
        if (!area.isActive()) return forbidden();
        Municipality municipality = municipalityDao.getParentOf(area);
        if (!municipality.isActive()) return forbidden();
        return ifPerson(session, Citizen.class);
    }

    @Override
    public boolean update(HttpSession session, Integer arg) {
        Reservation r = reservationDao.getById(arg);
        if (r == null || r.isEnded()) return forbidden();
        AreaPeriod areaPeriod = areaPeriodDao.getParentOf(r);
        if (areaPeriod.getPeriodEnd().isAfter(LocalTime.now())) return forbidden();
        return ifPerson(session, ControlStaff.class, MunicipalManager.class);
    }

    @Override
    public boolean delete(HttpSession session, Integer arg) {
        Reservation r = reservationDao.getById(arg);
        if (r == null || !r.isEnded() || r.isNotCancelled()) return forbidden();
        AreaPeriod areaPeriod = areaPeriodDao.getParentOf(r);
        if (areaPeriod.getPeriodEnd().isAfter(LocalTime.now())) return forbidden();
        Person user = getUser(session);
        return ifPerson(session, ControlStaff.class, MunicipalManager.class) || (user instanceof Citizen && r.getCitizen() == user.getId());
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

        if (r.getEnter() == null && r.getExit() != null) {
            errors.accept("exit", "No se puede definir si no ha entrado antes");
        } else if (r.getEnter() != null && r.getExit() != null && r.getExit().isBefore(r.getEnter())) {
            errors.accept("exit", "La salida debe ser posterior a la entrada");
        }

        boolean zonesExist = zones.stream().allMatch(z -> z != null && z.getArea() == area.getId());

        if (zonesExist) {
            if (r.getOccupied() < 1) {
                errors.accept("occupied", "Mínimo ha de haber 1 persona");
            } else {
                int capacity = zones.stream().mapToInt(Zone::getCapacity).sum();
                if (r.getOccupied() > capacity) {
                    errors.accept("occupied", "Demasiadas personas para la capacidad de las zonas selecionadas");
                }
            }

            Set<Integer> zoneUsed = reservationDao.getByAreaPeriod(r.getAreaPeriod()).stream().filter(o -> !o.equals(r) && o.getDate().equals(r.getDate()) && !o.isEnded()).flatMap(o -> reservationZoneDao.getChildsOf(o).stream()).map(ReservationZone::getZone).collect(Collectors.toSet());
            zoneUsed.retainAll(r.getZones());
            if (!zoneUsed.isEmpty()) {
                String zoneJoin = zoneUsed.stream().map(zoneDao::getById).map(Zone::getName).collect(Collectors.joining(", "));
                errors.accept("zones", String.format("Las zonas %s esta opcupadas el %s", zoneJoin, date));
            }
        } else {
            errors.accept("zone", "Alguna zona selecionada no esta dentro del area reservada");
        }
    }
}

// Comprobar que, para toda reserva que estás haciendo, no existra otra en:
// - en el área que estás reservando,
// - en el día que estás reservando
// - y en el horario que estás reservando
// Set<Reservation> reservations = new HashSet<>(this.reservationDao.getByAreaPeriod(r.getAreaPeriod()));
// Set<Integer> idReservedZones = new HashSet<>();
// for (Reservation reservation : reservations) {
//     if (reservation.getDate().equals(r.getDate()))
//         for (ReservationZone reservationZone : this.reservationZoneDao.getChildsOf(reservation))
//             idReservedZones.add(reservationZone.getZone());
// }
// idReservedZones ==> intersección entre las zonas reservadas de cualquier reserva (menos r) y las zonas reservadas en r
