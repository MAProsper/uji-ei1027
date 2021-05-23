package app.validator;

import app.dao.AreaDao;
import app.dao.MunicipalityDao;
import app.dao.ServiceDao;
import app.dao.ServiceTypeDao;
import app.model.Area;
import app.model.MunicipalManager;
import app.model.Municipality;
import app.model.generic.Activeable;
import app.model.generic.Person;
import app.validator.generic.FieldErrors;
import app.validator.generic.ScheduleableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ServiceValidator extends ScheduleableValidator<app.model.Service> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaDao areaDao;
    @Autowired ServiceTypeDao serviceTypeDao;
    @Autowired ServiceDao serviceDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        Area area = areaDao.getById(arg);
        if (!Activeable.isActive(area)) return forbidden();
        Municipality municipality = municipalityDao.getParentOf(area);
        if (!municipality.isActive()) return forbidden();
        Person user = getUser(session);
        return ifPerson(session, MunicipalManager.class) && ((MunicipalManager) user).getMunicipality() == municipality.getId();
    }

    @Override
    public void object(app.model.Service object, FieldErrors errors) {
        super.object(object, errors);

        if(this.serviceTypeDao.getById(object.getServiceType()) == null)
            errors.accept("serviceType", "El tipo de servicio no existe");
    }

    @Override
    public boolean add(HttpSession session, Integer arg) {
        return this.list(session, arg);
    }

    @Override
    public boolean update(HttpSession session, Integer arg) {
        app.model.Service service = this.serviceDao.getById(arg);
        if ( service == null || ! service.isUpdateable() ) return forbidden();
        Area area = this.areaDao.getParentOf(service);
        if (!Activeable.isActive(area)) return forbidden();
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
