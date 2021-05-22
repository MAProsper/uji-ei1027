package app.service;

import app.dao.*;
import app.model.*;
import app.model.generic.Person;
import app.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReservationService extends app.service.generic.Service<Reservation> {
    @Autowired protected ReservationZoneDao reservationZoneDao;
    @Autowired protected MunicipalityDao municipalityDao;
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

    public Municipality getMunicipality(Reservation r) {
        return municipalityDao.getById(getArea(r).getMunicipality());
    }

    public List<Zone> getZones(Reservation r) {
        return reservationZoneDao.getByReservation(r.getId()).stream().map(rz -> zoneDao.getById(rz.getZone())).collect(Collectors.toList());
    }

    public List<Zone> getAreaZone(Reservation r) {
        return zoneDao.getByArea(getArea(r).getId());
    }

    @Override
    public List<Reservation> listObjects(HttpSession session, Integer arg) {
        return reservationDao.getByCitizen(getUser(session).getId());
    }

    @Override
    public Map<String, Object> data(Reservation r) {
        if (r.getAreaPeriod() == 0) return Map.of();
        AreaPeriod areaPeriod = getAreaPeriod(r);
        LocalDate today = LocalDate.now();
        String zones = StringUtil.toString(getZones(r).stream().map(Zone::getName).collect(Collectors.joining(", ")));
        Map<String, LocalDate> date = Map.of("start", Collections.max(List.of(today, areaPeriod.scheduleStart)), "end", Collections.min(List.of(today.plusDays(2), areaPeriod.scheduleEnd)));
        return Map.of("citizen", getCitizen(r), "area", getArea(r), "municipality", getMunicipality(r), "zone", zones, "areaPeriod", areaPeriod, "areaZone", getAreaZone(r), "date", date);
    }

    @Override
    public Reservation addObject(HttpSession session, Integer arg) {
        Reservation r = super.addObject(session, arg);
        Person user = getUser(session);

        if (user != null) r.setCitizen(user.getId());
        if (arg != null) r.setAreaPeriod(arg);
        r.setOccupied(1);

        return r;
    }

    @Override
    public void addProcess(HttpSession session, Integer arg, Reservation object) {
        super.addProcess(session, arg, object);
        for (int zone : object.getZones()) {
            ReservationZone reservationZone = new ReservationZone();
            reservationZone.setReservation(object.getId());
            reservationZone.setZone(zone);
            reservationZoneDao.add(reservationZone);
        }
    }

    @Override
    public String getRedirect(HttpSession session, Integer arg) {
        return "../list";
    }
}