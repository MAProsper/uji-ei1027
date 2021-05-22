package app.service;

import app.dao.AreaDao;
import app.dao.MunicipalityDao;
import app.model.Area;
import app.model.Municipality;
import app.service.generic.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class AreaService extends PlaceService<Area> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaDao areaDao;

    public Municipality getMunicipality(Area area) {
        return municipalityDao.getById(area.getMunicipality());
    }

    @Override
    public List<Area> listObjects(HttpSession session, Integer arg) {
        return areaDao.getByMunicipality(arg);
    }

    @Override
    public Map<String, Object> data(Area area) {
        return Map.of("municipality", getMunicipality(area));
    }
}
