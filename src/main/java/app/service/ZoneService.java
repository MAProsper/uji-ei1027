package app.service;

import app.dao.AreaDao;
import app.dao.MunicipalityDao;
import app.dao.ZoneDao;
import app.model.Area;
import app.model.Zone;
import app.service.generic.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class ZoneService extends Service<Zone> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired ZoneDao zoneDao;
    @Autowired AreaDao areaDao;

    @Override
    public List<Zone> listObjects(HttpSession session, int arg) {
        return zoneDao.getByArea(arg);
    }

    @Override
    public Map<String, String> listRequestData(HttpSession session, int arg) {
        Area area = areaDao.getById(arg);
        return Map.of("municipality", municipalityDao.getById(area.getMunicipality()).getName(), "area", area.getName());
    }
}
