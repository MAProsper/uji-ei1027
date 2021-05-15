package app.validator;

import app.dao.AreaDao;
import app.model.AreaPeriod;
import app.validator.generic.ValidatorV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AreaPeriodValidator extends ValidatorV2<AreaPeriod> {
    @Autowired AreaDao areaDao;

    @Override
    public boolean list(HttpSession session, int arg) {
        if (areaDao.getById(arg) == null) return forbidden();
        return true;
    }
}
