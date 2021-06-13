package app.service;

import app.dao.*;
import app.model.*;
import app.model.generic.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @Autowired protected MailDao mailDao;

    @Override
    public List<Reservation> listObjects(HttpSession session, Integer arg) {
        Person user = getUser(session);
        if (user instanceof Citizen)
            return reservationDao.getChildsOf(user).stream().filter(Reservation::isNotCancelled).collect(Collectors.toList());
        if (user instanceof ControlStaff)
            return reservationDao.getByAreaPeriod(((ControlStaff) user).getAreaPeriod()).stream().filter(r -> !isEnded(r)).collect(Collectors.toList());
        return reservationDao.getByAreaPeriod(arg).stream().filter(r -> !isEnded(r)).collect(Collectors.toList());
    }

    public boolean isEnded(Reservation r) {
        if (r.isEnded()) return true;
        AreaPeriod areaPeriod = areaPeriodDao.getParentOf(r);
        return r.getDate().equals(LocalDate.now()) && areaPeriod.getPeriodEnd().isBefore(LocalTime.now());
    }

    @Override
    public Map<String, Object> data(Reservation r) {
        Map<String, Object> map = super.data(r);

        String reservationZone = reservationZoneDao.getChildsOf(r).stream().map(zoneDao::getParentOf).map(Zone::getName).collect(Collectors.joining(", "));
        map.put("zone", reservationZone);

        if (r.getId() != 0) {
            map.put("origin", reservationDao.getById(r.getId()));
        }

        if (r.getCitizen() != 0) {
            Citizen citizen = citizenDao.getParentOf(r);
            map.put("citizen", citizen);
        }

        if (r.getAreaPeriod() != 0) {
            LocalTime now = LocalTime.now();
            LocalDate minDay = LocalDate.now();
            LocalDate maxDay = minDay.plusDays(2);
            AreaPeriod areaPeriod = areaPeriodDao.getParentOf(r);
            Area area = areaDao.getParentOf(areaPeriod);
            List<Zone> areaZone = zoneDao.getChildsOf(area).stream().filter(Zone::isActive).collect(Collectors.toList());
            Municipality municipality = municipalityDao.getParentOf(area);
            int capacity = areaZone.stream().mapToInt(Zone::getCapacity).sum();
            if (now.isAfter(areaPeriod.getPeriodEnd())) minDay = minDay.plusDays(1);
            Map<String, LocalDate> date = Map.of("start", Collections.max(List.of(minDay, areaPeriod.scheduleStart)), "end", areaPeriod.scheduleEnd == null ? maxDay : Collections.min(List.of(maxDay, areaPeriod.scheduleEnd)));
            map.putAll(Map.of("area", area, "municipality", municipality, "areaPeriod", areaPeriod, "areaZone", areaZone, "date", date, "capacity", capacity));

            if (r.getDate() != null)
                map.put("ended", isEnded(r));
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
    public Reservation updateObject(HttpSession session, Integer arg) {
        LocalTime now = LocalTime.now();
        Reservation r = super.updateObject(session, arg);
        r.setZones(reservationZoneDao.getChildsOf(r).stream().map(ReservationZone::getZone).collect(Collectors.toList()));
        //if (r.getEnter() == null) r.setEnter(now);
        //else if (r.getExit() == null) r.setExit(now);
        return r;
    }

    protected void updateZones(Reservation r) {
        reservationZoneDao.getChildsOf(r).forEach(z -> reservationZoneDao.delete(z.getId()));
        for (int zone : r.getZones()) {
            ReservationZone reservationZone = new ReservationZone();
            reservationZone.setReservation(r.getId());
            reservationZone.setZone(zone);
            reservationZoneDao.add(reservationZone);
        }
    }

    @Override
    public void updateProcess(HttpSession session, Integer arg, Reservation object) {
        super.updateProcess(session, arg, object);
        updateZones(object);
    }

    @Override
    public void addProcess(HttpSession session, Integer arg, Reservation object) {
        super.addProcess(session, arg, object);
        updateZones(object);
        Map<String, Object> data = data(object);
        AreaPeriod areaPeriod = areaPeriodDao.getParentOf(object);
        Citizen citizen = citizenDao.getParentOf(object);
        Area area = areaDao.getParentOf(areaPeriod);
        Mail mail = new Mail();
        mail.setMail(citizen.getMail());
        mail.setSubject(String.format("Registrado reserva de %s", area.getName()));
        mail.setBody(String.format("Se le ha reservado las zonas %s para el dia %s de %s", data.get("zone"), object.getDate(), areaPeriod.toPeriodString()));
        mailDao.add(mail);
    }

    @Override
    public void deleteProcess(HttpSession session, Integer arg) {
        super.deleteProcess(session, arg);
        Reservation object = reservationDao.getById(arg);
        Map<String, Object> data = data(object);
        AreaPeriod areaPeriod = areaPeriodDao.getParentOf(object);
        Citizen citizen = citizenDao.getParentOf(object);
        Area area = areaDao.getParentOf(areaPeriod);
        Mail mail = new Mail();
        mail.setMail(citizen.getMail());
        mail.setSubject(String.format("Cancelada reserva de %s", area.getName()));
        mail.setBody(String.format("Se le ha cancelado la reserva de las zonas %s para el dia %s de %s", data.get("zone"), object.getDate(), areaPeriod.toPeriodString()));
        mailDao.add(mail);
    }

    @Override
    public String redirectParent(HttpSession session, Integer arg) {
        List<Reservation> reservations = listObjects(session, arg);
        return String.format("../view/%d", reservations.get(0).getId());
    }

    @Override
    public String redirectSelf(HttpSession session, Integer arg) {
        Person user = getUser(session);
        if (user instanceof Citizen || user instanceof ControlStaff) return "../list";
        return String.format("../list/%d", reservationDao.getById(arg).getAreaPeriod());
    }
}