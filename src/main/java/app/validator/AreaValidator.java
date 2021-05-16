package app.validator;

import app.dao.MunicipalityDao;
import app.model.Area;
import app.validator.generic.PlaceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AreaValidator extends PlaceValidator<Area> {
    @Autowired MunicipalityDao municipalityDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        return arg != null && municipalityDao.getById(arg) != null || forbidden();
    }
}
