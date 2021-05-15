package app.service;

import app.dao.AreaDao;
import app.dao.AreaPeriodDao;
import app.dao.MunicipalityDao;
import app.model.Area;
import app.model.AreaPeriod;
import app.service.generic.ScheduableService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class AreaPeriodService extends ScheduableService<AreaPeriod> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired AreaDao areaDao;

    @Override
    public List<AreaPeriod> listObjects(HttpSession session, int arg) {
        return areaPeriodDao.getByArea(arg);
    }

    @Override
    public Map<String, String> listRequestData(HttpSession session, int arg) {
        Area area = areaDao.getById(arg);
        return Map.of("municipality", municipalityDao.getById(area.getMunicipality()).getName(), "area", area.getName());
    }
}
