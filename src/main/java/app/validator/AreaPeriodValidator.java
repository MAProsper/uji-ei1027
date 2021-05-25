package app.validator;

import app.dao.AreaDao;
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
}
