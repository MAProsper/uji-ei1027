package app.validator;

import app.dao.AreaDao;
import app.dao.MunicipalityDao;
import app.model.Area;
import app.model.MunicipalManager;
import app.model.Municipality;
import app.model.Zone;
import app.model.generic.Activeable;
import app.model.generic.Person;
import app.validator.generic.PlaceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ZoneValidator extends PlaceValidator<Zone> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaDao areaDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg == null) return forbidden();
        Person user = getUser(session);
        if (user instanceof MunicipalManager) {
            Area area = areaDao.getById(arg);
            if (area == null) return forbidden();
            Municipality municipality = municipalityDao.getParentOf(area);
            if (municipality == null) return forbidden();
            return ((MunicipalManager) user).getMunicipality() == municipality.getId();
        }
        return false;
    }
}
