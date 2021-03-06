package app.service;

import app.dao.AreaDao;
import app.dao.AreaPeriodDao;
import app.dao.MunicipalityDao;
import app.model.Area;
import app.model.AreaPeriod;
import app.model.MunicipalManager;
import app.model.generic.Person;
import app.service.generic.ScheduableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AreaPeriodService extends ScheduableService<AreaPeriod> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired AreaDao areaDao;

    @Override
    public List<AreaPeriod> listObjects(HttpSession session, Integer arg) {
        Person user = getUser(session);
        Area area = areaDao.getById(arg);
        List<AreaPeriod> areaPeriod = areaPeriodDao.getChildsOf(area);
        if (user instanceof MunicipalManager && ((MunicipalManager) user).getMunicipality() == area.getMunicipality())
            return areaPeriod;
        return areaPeriod.stream().filter(AreaPeriod::isActive).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> data(AreaPeriod areaPeriod) {
        Map<String, Object> data = super.data(areaPeriod);
        LocalDate today = LocalDate.now();
        Area area = areaDao.getParentOf(areaPeriod);
        data.putAll(Map.of("municipality", municipalityDao.getParentOf(area), "area", area, "today", today));
        return data;
    }

    @Override
    public AreaPeriod addObject(HttpSession session, Integer arg) {
        AreaPeriod ap = super.addObject(session, arg);
        ap.setArea(arg);
        ap.setScheduleStart(LocalDate.now());
        return ap;
    }

    @Override
    public String redirectSelf(HttpSession session, Integer arg) {
        return String.format("../list/%d", areaPeriodDao.getById(arg).getArea());
    }

}
