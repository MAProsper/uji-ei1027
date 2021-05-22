package app.service;

import app.dao.AreaDao;
import app.dao.AreaPeriodDao;
import app.dao.MunicipalityDao;
import app.model.Area;
import app.model.AreaPeriod;
import app.model.Municipality;
import app.service.generic.ScheduableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class AreaPeriodService extends ScheduableService<AreaPeriod> {
    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired AreaService areaService;
    @Autowired AreaDao areaDao;

    public Area getArea(AreaPeriod areaPeriod) {
        return areaDao.getById(areaPeriod.getArea());
    }

    public Municipality getMunicipality(AreaPeriod areaPeriod) {
        return areaService.getMunicipality(getArea(areaPeriod));
    }

    @Override
    public List<AreaPeriod> listObjects(HttpSession session, Integer arg) {
        return areaPeriodDao.getByArea(arg);
    }

    @Override
    public Map<String, Object> data(AreaPeriod areaPeriod) {
        return Map.of("municipality", getMunicipality(areaPeriod), "area", getArea(areaPeriod));
    }

    @Override
    public AreaPeriod addObject(HttpSession session, Integer arg) {
        AreaPeriod ap = super.addObject(session, arg);
        ap.setArea(arg);
        return ap;
    }
}
