package app.validator;

import app.dao.AreaDao;
import app.dao.AreaPeriodDao;
import app.dao.ControlStaffDao;
import app.dao.MunicipalityDao;
import app.model.*;
import app.model.generic.Activeable;
import app.model.generic.Person;
import app.validator.generic.PersonValidator;
import groovyjarjarantlr.preprocessor.PreprocessorTokenTypes;
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
    private boolean comprobacion(HttpSession session, Integer areaPeriodId){
        AreaPeriod areaPeriod = areaPeriodDao.getById(areaPeriodId);
        if (!Activeable.isActive(areaPeriod)) return forbidden();
        Area area = areaDao.getParentOf(areaPeriod);
        if (!area.isActive()) return forbidden();
        Municipality municipality = municipalityDao.getParentOf(area);
        if (!municipality.isActive()) return forbidden();
        Person person = getUser(session);
        if (! (person instanceof MunicipalManager) ) return false;
        MunicipalManager municipalManager = (MunicipalManager) person;
        return municipality.getId() == municipalManager.getMunicipality();
    }

    //arg id del areaPeriod
    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg == null) return ifPerson(session, ControlStaff.class);
        return this.comprobacion(session, arg);
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
        if ( controlStaff == null || ! controlStaff.isActive()) return forbidden();
        return this.comprobacion(session, controlStaff.getAreaPeriod());
    }

    //Argumento el id del control staff
    @Override
    public boolean delete(HttpSession session, Integer arg) {
        if (arg == null) return forbidden();
        ControlStaff controlStaff = this.controlStaffDao.getById(arg);
        if ( controlStaff == null || ! controlStaff.isActive()) return forbidden();
        return this.comprobacion(session, arg);
    }


}
