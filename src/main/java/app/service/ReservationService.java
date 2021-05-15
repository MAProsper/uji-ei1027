package app.service;

import app.dao.*;
import app.model.*;
import app.model.generic.Person;
import app.service.generic.Service;
import app.service.generic.ServiceV2;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ReservationService extends ServiceV2<Reservation> {
    @Autowired protected ReservationZoneDao reservationZoneDao;
    @Autowired protected ReservationDao reservationDao;
    @Autowired protected AreaPeriodDao areaPeriodDao;
    @Autowired MunicipalityDao municipalityDao;
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
    public List<Reservation> listObjects(HttpSession session) {
        Citizen citizen = (Citizen) session.getAttribute("user");
        return reservationDao.getByCitizen(citizen.getId());
    }

    @Override
    public Map<String, String> listObjectData(Reservation r) {
        String citizen = getCitizen(r).toIdentificationString();
        String area = getArea(r).getName();
        String zones = getZones(r).stream().map(Zone::getName).collect(Collectors.joining(", "));
        String areaPeriod = getAreaPeriod(r).toPeriodString();
        return Map.of("citizen", citizen, "area", area, "zones", zones, "areaPeriod", areaPeriod);
    }

    @Override
    public Reservation addObject(HttpSession session, int arg) {
        Reservation r = super.addObject(session, arg);
        r.setAreaPeriod(arg);
        return r;
    }

    @Override
    public Map<String, String> addRequestData(HttpSession session, int arg) {
        Area area = areaDao.getById(arg);
        return Map.of("municipality", municipalityDao.getById(area.getMunicipality()).getName(), "area", area.getName());
    }

    @Override
    public Map<String, String> updateRequestData(HttpSession session, int arg) {
        return addRequestData(session, arg);
    }

    @Override
    public String addRedirect(HttpSession session, int arg) {
        return "../list";
    }
}