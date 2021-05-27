package app.validator;

import app.dao.AreaDao;
import app.dao.AreaPeriodDao;
import app.dao.MunicipalityDao;
import app.model.*;
import app.model.generic.Activeable;
import app.model.generic.Person;
import app.validator.generic.ScheduleableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AreaPeriodValidator extends ScheduleableValidator<AreaPeriod> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaDao areaDao;
    @Autowired AreaPeriodDao areaPeriodDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg == null) return forbidden();
        Person user = getUser(session);
        if (user == null || user instanceof Citizen) {
            Area area = areaDao.getById(arg);
            if (area == null) return forbidden();
            if (!area.isActive()) return false;
            Municipality municipality = municipalityDao.getParentOf(area);
            if (municipality == null) return forbidden();
            return municipality.isActive();
        } else if (user instanceof MunicipalManager) {
            Area area = areaDao.getById(arg);
            if (area == null) return forbidden();
            Municipality municipality = municipalityDao.getParentOf(area);
            if (municipality == null) return forbidden();
            return ((MunicipalManager) user).getMunicipality() == municipality.getId();
        }
        return false;
    }

    @Override
    public boolean add(HttpSession session, Integer arg) {
        Area area = areaDao.getById(arg);
        if (!Activeable.isActive(area)) return forbidden();
        Municipality municipality = municipalityDao.getParentOf(area);
        if (!municipality.isActive()) return forbidden();
        Person user = getUser(session);
        return ifPerson(session, MunicipalManager.class) && ((MunicipalManager) user).getMunicipality() == municipality.getId();
    }

    @Override
    public boolean update(HttpSession session, Integer arg) {
        AreaPeriod service = this.areaPeriodDao.getById(arg);
        if (service == null || !service.isEnded()) return forbidden();
        Area area = this.areaDao.getParentOf(service);
        if (!area.isActive()) return forbidden();
        Municipality municipality = municipalityDao.getParentOf(area);
        if (!municipality.isActive()) return forbidden();
        Person user = getUser(session);
        return ifPerson(session, MunicipalManager.class) && ((MunicipalManager) user).getMunicipality() == municipality.getId();
    }

    @Override
    public boolean delete(HttpSession session, Integer arg) {
        return this.update(session, arg);
    }
}
