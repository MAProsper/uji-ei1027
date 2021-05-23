package app.validator;

import app.dao.MunicipalityDao;
import app.model.Area;
import app.model.Citizen;
import app.model.MunicipalManager;
import app.model.generic.Activeable;
import app.model.generic.Person;
import app.validator.generic.PlaceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AreaValidator extends PlaceValidator<Area> {
    @Autowired MunicipalityDao municipalityDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        Person user = getUser(session);
        if (arg == null) return ifPerson(session, MunicipalManager.class);
        if (!Activeable.isActive(municipalityDao.getById(arg))) return forbidden();
        return user == null || user instanceof Citizen;
    }
}
