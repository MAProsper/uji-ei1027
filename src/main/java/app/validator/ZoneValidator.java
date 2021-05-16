package app.validator;

import app.dao.AreaDao;
import app.model.MunicipalManager;
import app.model.Zone;
import app.validator.generic.PlaceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ZoneValidator extends PlaceValidator<Zone> {
    @Autowired AreaDao areaDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        return arg == null || areaDao.getById(arg) == null ? forbidden() : ifPerson(session, MunicipalManager.class);
    }
}
