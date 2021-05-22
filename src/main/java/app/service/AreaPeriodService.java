package app.service;

import app.dao.AreaDao;
import app.dao.AreaPeriodDao;
import app.dao.MunicipalityDao;
import app.model.Area;
import app.model.AreaPeriod;
import app.service.generic.ScheduableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class AreaPeriodService extends ScheduableService<AreaPeriod> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired AreaDao areaDao;
    
    @Override
    public List<AreaPeriod> listObjects(HttpSession session, Integer arg) {
        return areaPeriodDao.getByArea(arg);
    }

    @Override
    public Map<String, Object> data(AreaPeriod areaPeriod) {
        Area area = areaDao.getParentOf(areaPeriod);
        return Map.of("municipality", municipalityDao.getParentOf(area), "area", area);
    }

    @Override
    public AreaPeriod addObject(HttpSession session, Integer arg) {
        AreaPeriod ap = super.addObject(session, arg);
        ap.setArea(arg);
        return ap;
    }
}
