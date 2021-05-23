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
        Person user = getUser(session);
        Area area = areaDao.getById(arg);
        if (!Activeable.isActive(area)) return forbidden();
        Municipality municipality = municipalityDao.getParentOf(area);
        if (!municipality.isActive()) return forbidden();
        return user == null || user instanceof Citizen || (user instanceof MunicipalManager && ((MunicipalManager) user).getMunicipality() == municipality.getId());
    }
}
