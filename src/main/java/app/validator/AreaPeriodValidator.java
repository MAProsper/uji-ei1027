package app.validator;

import app.dao.AreaDao;
import app.model.AreaPeriod;
import app.model.generic.Activeable;
import app.validator.generic.ScheduleableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AreaPeriodValidator extends ScheduleableValidator<AreaPeriod> {
    @Autowired AreaDao areaDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg == null || !Activeable.isActive(areaDao.getById(arg))) return forbidden();
        return true;
    }
}
