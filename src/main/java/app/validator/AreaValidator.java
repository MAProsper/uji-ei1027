package app.validator;

import app.dao.MunicipalityDao;
import app.model.Area;
import app.validator.generic.ValidatorV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AreaValidator extends ValidatorV2<Area> {
    @Autowired MunicipalityDao municipalityDao;

    @Override
    public boolean list(HttpSession session, int arg) {
        if (municipalityDao.getById(arg) == null) return forbidden();
        return true;
    }
}
