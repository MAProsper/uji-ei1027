package app.validator;

import app.dao.AreaDao;
import app.dao.MunicipalityDao;
import app.dao.ZoneDao;
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
    @Autowired ZoneDao zoneDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg == null) return forbidden();
        Person user = getUser(session);
        if (user instanceof MunicipalManager) {
            Area area = areaDao.getById(arg);
            if (area == null) return forbidden();
            Municipality municipality = municipalityDao.getParentOf(area);
            return ((MunicipalManager) user).getMunicipality() == municipality.getId();
        }
        return false;
    }

    @Override
    public boolean add(HttpSession session, Integer arg) {
        Area area = areaDao.getById(arg);
        if (!Activeable.isActive(area)) return forbidden();
        Municipality municipality = municipalityDao.getParentOf(area);
        if (!municipality.isActive()) return forbidden();
        Person user = getUser(session);
        return ifPerson(session, MunicipalManager.class) && ((MunicipalManager) user).getMunicipality() == municipality.getId();
    }

    @Override
    public boolean update(HttpSession session, Integer arg) {
        Zone service = this.zoneDao.getById(arg);
        if (!Activeable.isActive(service)) return forbidden();
        Area area = this.areaDao.getParentOf(service);
        if (!area.isActive()) return forbidden();
        Municipality municipality = municipalityDao.getParentOf(area);
        if (!municipality.isActive()) return forbidden();
        Person user = getUser(session);
        return ifPerson(session, MunicipalManager.class) && ((MunicipalManager) user).getMunicipality() == municipality.getId();
    }

    @Override
    public boolean delete(HttpSession session, Integer arg) {
        return this.update(session, arg);
    }
}
