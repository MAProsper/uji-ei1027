package app.validator;

import app.dao.AreaDao;
import app.dao.AreaPeriodDao;
import app.dao.ControlStaffDao;
import app.dao.MunicipalityDao;
import app.model.*;
import app.model.generic.Activeable;
import app.model.generic.Person;
import app.validator.generic.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ControlStaffValidator extends PersonValidator<ControlStaff> {

    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaDao areaDao;
    @Autowired ControlStaffDao controlStaffDao;

    // A partir de la sessión y el id del "areaPeriod" comprueba que sea válido
    private boolean comprobacion(HttpSession session, Integer areaPeriodId) {
        AreaPeriod areaPeriod = areaPeriodDao.getById(areaPeriodId);
        if (!Activeable.isActive(areaPeriod)) return forbidden();
        Area area = areaDao.getParentOf(areaPeriod);
        if (!area.isActive()) return forbidden();
        Municipality municipality = municipalityDao.getParentOf(area);
        if (!municipality.isActive()) return forbidden();
        Person person = getUser(session);
        if (!(person instanceof MunicipalManager)) return false;
        MunicipalManager municipalManager = (MunicipalManager) person;
        return municipality.getId() == municipalManager.getMunicipality();
    }

    //arg = id del areaPeriod
    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg == null) return forbidden();
        Person user = getUser(session);
        if (user == null || user instanceof Citizen) {
            AreaPeriod areaPeriod = this.areaPeriodDao.getById(arg);
            if (areaPeriod == null) return forbidden();
            if (!areaPeriod.isEnded()) return false;
            Area area = areaDao.getParentOf(areaPeriod);
            if (area == null) return forbidden();
            if (!area.isActive()) return false;
            Municipality municipality = municipalityDao.getParentOf(area);
            if (municipality == null) return forbidden();
            return municipality.isActive();
        } else if (user instanceof MunicipalManager) {
            AreaPeriod areaPeriod = this.areaPeriodDao.getById(arg);
            if (areaPeriod == null) return forbidden();
            Area area = areaDao.getParentOf(areaPeriod);
            if (area == null) return forbidden();
            Municipality municipality = municipalityDao.getParentOf(area);
            if (municipality == null) return forbidden();
            return ((MunicipalManager) user).getMunicipality() == municipality.getId();
        }
        return false;
    }

    //arg id del areaPeriod
    @Override
    public boolean add(HttpSession session, Integer arg) {
        if (arg == null) return forbidden();
        return this.comprobacion(session, arg);
    }

    //Argumento: el id del control staff
    @Override
    public boolean update(HttpSession session, Integer arg) {
        if (arg == null) return ifPerson(session, ControlStaff.class);
        ControlStaff controlStaff = this.controlStaffDao.getById(arg);
        if (controlStaff == null || !controlStaff.isActive()) return forbidden();
        return this.comprobacion(session, controlStaff.getAreaPeriod());
    }

    //Argumento el id del control staff
    @Override
    public boolean delete(HttpSession session, Integer arg) {
        if (arg == null) return forbidden();
        ControlStaff controlStaff = this.controlStaffDao.getById(arg);
        if (controlStaff == null || !controlStaff.isActive()) return forbidden();
        return this.comprobacion(session, arg);
    }


}
