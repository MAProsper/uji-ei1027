package app.validator;

import app.dao.MunicipalityDao;
import app.model.Area;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AreaValidator extends Validator<Area> {
    @Autowired MunicipalityDao municipalityDao;

    @Override
    public boolean list(HttpSession session, int arg) {
        if (municipalityDao.getById(arg) == null) return forbidden();
        return true;
    }
}
