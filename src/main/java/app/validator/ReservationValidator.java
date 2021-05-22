package app.validator;

import app.dao.AreaDao;
import app.dao.AreaPeriodDao;
import app.dao.ReservationDao;
import app.dao.ZoneDao;
import app.model.*;
import app.service.ReservationService;
import app.validator.generic.FieldError;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationValidator extends Validator<Reservation> {
    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired ReservationDao reservationDao;
    @Autowired AreaDao areaDao;
    @Autowired ZoneDao zoneDao;

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
    public void object(Reservation r, FieldError errors) {
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
                errors.accept("occupied", "Minimo ha de haber 1 persona");
            } else {
                int capacity = zones.stream().mapToInt(Zone::getCapacity).sum();
                if (r.getOccupied() > capacity)
                    errors.accept("occupied", "Demasiadas personas para la capacidad de las zonas selecionadas");
            }
        } else {
            errors.accept("zone", "Alguna zona selecionada no esta dentro del area reservada");
        }
    }
}
