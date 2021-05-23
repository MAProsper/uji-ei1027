package app.service;

import app.dao.*;
import app.model.*;
import app.model.generic.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
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

    @Override
    public List<Reservation> listObjects(HttpSession session, Integer arg) {
        Person user = getUser(session);
        if (user instanceof Citizen) return reservationDao.getChildsOf(user).stream().filter(r -> !r.isCancelled()).collect(Collectors.toList());
        if (user instanceof ControlStaff) return reservationDao.getByAreaPeriod(((ControlStaff) user).getAreaPeriod()).stream().filter(Reservation::isActive).collect(Collectors.toList());
        return reservationDao.getByAreaPeriod(arg).stream().filter(Reservation::isActive).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> data(Reservation r) {
        Map<String, Object> map = new HashMap<>();

        String reservationZone = reservationZoneDao.getChildsOf(r).stream().map(zoneDao::getParentOf).map(Zone::getName).collect(Collectors.joining(", "));
        map.put("zone", reservationZone);

        if (r.getCitizen() != 0) {
            Citizen citizen = citizenDao.getParentOf(r);
            map.put("citizen", citizen);
        }

        if (r.getAreaPeriod() != 0) {
            LocalDate today = LocalDate.now();
            AreaPeriod areaPeriod = areaPeriodDao.getParentOf(r);
            Area area = areaDao.getParentOf(areaPeriod);
            List<Zone> areaZone = zoneDao.getChildsOf(area);
            Municipality municipality = municipalityDao.getParentOf(area);
            int capacity = areaZone.stream().mapToInt(Zone::getCapacity).sum();
            Map<String, LocalDate> date = Map.of("start", Collections.max(List.of(today, areaPeriod.scheduleStart)), "end", Collections.min(List.of(today.plusDays(2), areaPeriod.scheduleEnd)));
            map.putAll(Map.of("area", area, "municipality", municipality, "areaPeriod", areaPeriod, "areaZone", areaZone, "date", date, "capacity", capacity));
        }

        return map;
    }

    @Override
    public Reservation addObject(HttpSession session, Integer arg) {
        Reservation r = super.addObject(session, arg);
        Person user = getUser(session);
        if (arg != null) r.setAreaPeriod(arg);
        if (user instanceof Citizen) r.setCitizen(user.getId());
        else if (user instanceof ControlStaff) r.setAreaPeriod(((ControlStaff) user).getAreaPeriod());
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
        if (getUser(session) instanceof Citizen) return "../list";
        return super.getRedirect(session, arg);
    }
}