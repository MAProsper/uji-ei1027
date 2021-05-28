package app.validator;

import app.dao.AreaDao;
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
    @Autowired AreaDao areaDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        Person user = getUser(session);
        if (arg == null) return ifPerson(session, MunicipalManager.class);
        if (!Activeable.isActive(municipalityDao.getById(arg))) return forbidden();
        return user == null || user instanceof Citizen;
    }

    @Override
    public boolean add(HttpSession session, Integer arg) {
        if (arg == null) {
            Person user = getUser(session);
            return user instanceof MunicipalManager;
        }
        return forbidden();
    }

    @Override
    public boolean update(HttpSession session, Integer arg) {
        return delete(session, arg);
    }

    @Override
    public boolean delete(HttpSession session, Integer arg) {
        if (arg != null) {
            Person user = getUser(session);
            if (user instanceof MunicipalManager) {
                Area area = areaDao.getById(arg);
                if (area == null || !area.isActive()) {
                    return forbidden();
                }
                return area.getMunicipality() == ((MunicipalManager) user).getMunicipality();
            }
            return false;
        }
        return forbidden();
    }
}
