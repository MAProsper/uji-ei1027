package app.service;

import app.dao.*;
import app.model.*;
import app.model.generic.Person;
import app.service.generic.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
        return citizenDao.getById(r.getCitizen());
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
    public List<Reservation> getAll(HttpSession session) {
        Person person = (Person) session.getAttribute("user");
        if (person instanceof Citizen) return reservationDao.getByCitizen(person.getId());
        return Collections.emptyList();
    }

    @Override
    public Map<String,String> otherData(Reservation r) {
        String citizen = getCitizen(r).toIdentificationString();
        String area = getArea(r).getName();
        String zones = getZones(r).stream().map(Zone::getName).collect(Collectors.joining(", "));
        String areaPeriod = getAreaPeriod(r).toPeriodString();
        return Map.of("citizen", citizen, "area", area, "zones", zones, "areaPeriod", areaPeriod);
    }
}