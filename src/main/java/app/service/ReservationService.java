package app.service;

import app.dao.*;
import app.model.*;
import org.springframework.stereotype.Service;
import app.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReservationService extends app.service.generic.Service<Reservation> {
    @Autowired protected ReservationZoneDao reservationZoneDao;
    @Autowired protected ReservationDao reservationDao;
    @Autowired protected AreaPeriodDao areaPeriodDao;
    @Autowired protected MunicipalityDao municipalityDao;
    @Autowired protected CitizenDao citizenDao;
    @Autowired protected AreaDao areaDao;
    @Autowired protected ZoneDao zoneDao;

    protected Citizen getCitizen(Reservation r) {
        return citizenDao.getById(r.getCitizen());
    }

    protected AreaPeriod getAreaPeriod(Reservation r) {
        return areaPeriodDao.getById(r.getAreaPeriod());
    }

    protected Area getArea(Reservation r) {
        return areaDao.getById(getAreaPeriod(r).getArea());
    }

    protected List<Zone> getZones(Reservation r) {
        return reservationZoneDao.getByReservation(r.getId()).stream().map(rz -> zoneDao.getById(rz.getZone())).collect(Collectors.toList());
    }

    @Override
    public List<Reservation> listObjects(HttpSession session, Integer arg) {
        return reservationDao.getByCitizen(getUser(session).getId());
    }

    @Override
    public Map<String, Object> listObjectData(Reservation r) {
        String area = getArea(r).getName();
        String areaPeriod = getAreaPeriod(r).toPeriodString();
        String citizen = getCitizen(r).toIdentificationString();
        String zones = StringUtil.toString(getZones(r).stream().map(Zone::getName).collect(Collectors.joining(", ")));
        return Map.of("citizen", citizen, "area", area, "zones", zones, "areaPeriod", areaPeriod);
    }

    @Override
    public void requestProcess(HttpSession session, Integer arg, Reservation r) {
        super.requestProcess(session, arg, r);
        r.setAreaPeriod(arg);
    }

    @Override
    public Map<String, Object> processData(HttpSession session, Integer arg) {
        Area area = areaDao.getById(arg);
        return Map.of("municipality", municipalityDao.getById(area.getMunicipality()).getName(), "area", area.getName());
    }

    @Override
    public String getRedirect(HttpSession session, Integer arg) {
        return "../list";
    }
}