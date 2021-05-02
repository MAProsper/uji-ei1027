package app.service;

import app.dao.*;
import app.model.*;
import app.model.generic.Person;
import app.service.generic.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ReservationService extends Service<Reservation> {
    @Autowired protected ReservationZoneDao reservationZoneDao;
    @Autowired protected ReservationDao reservationDao;
    @Autowired protected AreaPeriodDao areaPeriodDao;
    @Autowired protected CitizenDao citizenDao;
    @Autowired protected AreaDao areaDao;
    @Autowired protected ZoneDao zoneDao;

    public Citizen getCitizen(Reservation r) {
        return citizenDao.getById(r.getId());
    }

    public AreaPeriod getAreaPeriod(Reservation r) {
        return areaPeriodDao.getById(r.getAreaPeriod());
    }

    public Area getArea(Reservation r) {
        return areaDao.getById(getAreaPeriod(r).getArea());
    }

    public List<Zone> getZones(Reservation r) {
        return reservationZoneDao.getByReservation(r.getId()).stream().map(rz -> zoneDao.getById(rz.getZone())).collect(Collectors.toList());
    }

    @Override
    public List<String> getColumnNames() {
        return List.of("Codigo", "Reservado por", "Numero de personas", "Area", "Zonas", "Dia", "Horario", "Entrada y salida");
    }

    @Override
    public List<Reservation> getAll(HttpSession session) {
        Person person = (Person) session.getAttribute("user");
        if (person instanceof Citizen) return reservationDao.getByCitizen(person.getId());
        return reservationDao.getAll();
    }

    @Override
    public List<Object> mapRow(Reservation r) {
        String citizen = getCitizen(r).toIdentificationString();
        String area = getArea(r).getName();
        String zones = getZones(r).stream().map(Zone::getName).collect(Collectors.joining(", "));
        String areaPeriod = getAreaPeriod(r).toPeriodString();
        String period = r.toPeriodString();
        return List.of(r.getCode(), citizen, r.getOccupied(), area, zones, r.getDate(), areaPeriod, period);
    }
}