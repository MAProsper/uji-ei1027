package app.service;

import app.dao.AreaDao;
import app.dao.MunicipalityDao;
import app.model.Area;
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
    @Autowired AreaDao areaDao;

    @Override
    public List<Area> listObjects(HttpSession session, Integer arg) {
        return areaDao.getByMunicipality(arg).stream().filter(Area::isActive).collect(Collectors.toList());
    }

    @Override
    public Area addObject(HttpSession session, Integer arg) {
        Area area = super.addObject(session, arg);
        area.setMunicipality(arg);
        return area;
    }

    @Override
    public Map<String, Object> data(Area area) {
        return Map.of("municipality", municipalityDao.getParentOf(area));
    }
}
