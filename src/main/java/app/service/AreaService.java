package app.service;

import app.dao.*;
import app.model.*;
import app.model.generic.Person;
import app.service.generic.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AreaService extends PlaceService<Area> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired ReservationDao reservationDao;
    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired ZoneDao zoneDao;
    @Autowired AreaDao areaDao;

    @Override
    public List<Area> listObjects(HttpSession session, Integer arg) {
        Person user = getUser(session);
        if (user instanceof MunicipalManager)
            return areaDao.getByMunicipality(((MunicipalManager) user).getMunicipality());
        return areaDao.getByMunicipality(arg).stream().filter(Area::isActive).collect(Collectors.toList());
    }

    @Override
    public Area addObject(HttpSession session, Integer arg) {
        Area area = super.addObject(session, arg);
        Person user = getUser(session);
        if (user instanceof MunicipalManager)
            area.setMunicipality(((MunicipalManager) user).getMunicipality());
        else
            area.setMunicipality(arg);
        return area;
    }

    @Override
    public Map<String, Object> data(Area area) {
        long active = areaPeriodDao.getChildsOf(area).stream().filter(AreaPeriod::isActive).flatMap(ap -> reservationDao.getChildsOf(ap).stream()).filter(Reservation::isActive).mapToInt(Reservation::getOccupied).sum();
        int capacity = zoneDao.getChildsOf(area).stream().filter(Zone::isActive).mapToInt(Zone::getCapacity).sum();
        if (capacity == 0) capacity = 1;
        String occupied = String.format("%d%%", (active * 100) / capacity);
        return Map.of("municipality", municipalityDao.getParentOf(area), "occupied", occupied);
    }

    @Override
    public String getRedirect(HttpSession session, Integer arg) {
        return "../list";
    }
}