package app.validator;

import app.dao.MunicipalityDao;
import app.model.Area;
import app.model.Municipality;
import app.model.generic.Activeable;
import app.validator.generic.PlaceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AreaValidator extends PlaceValidator<Area> {
    @Autowired MunicipalityDao municipalityDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg == null || !Activeable.isActive(municipalityDao.getById(arg))) return forbidden();
        return true;
    }
}
