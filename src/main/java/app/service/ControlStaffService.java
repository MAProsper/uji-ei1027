package app.service;

import app.dao.AreaDao;
import app.dao.AreaPeriodDao;
import app.dao.ControlStaffDao;
import app.dao.MunicipalityDao;
import app.model.Area;
import app.model.AreaPeriod;
import app.model.ControlStaff;
import app.model.Municipality;
import app.service.generic.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ControlStaffService extends PersonService<ControlStaff> {
    @Autowired ControlStaffDao controlStaffDao;
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaDao areaDao;
    @Autowired AreaPeriodDao areaPeriodDao;

    @Override
    public void updateProcess(HttpSession session, Integer arg, ControlStaff object) {
        super.updateProcess(session, arg, object);
        if (super.getUser(session) instanceof ControlStaff)
            super.addSession(session, object);
    }

    @Override
    public List<ControlStaff> listObjects(HttpSession session, Integer arg) {
        // Caso mostrar datos del control Staff
        if (arg == null) return Collections.singletonList((ControlStaff) super.getUser(session));
        // Caso mostrar todos los del areaPeriod (arg = areaPeriod)
        else return this.controlStaffDao.getByAreaPeriod(arg);
    }

    @Override
    public ControlStaff addObject(HttpSession session, Integer arg) {
        ControlStaff controlStaff;
        if (arg != null) {
            controlStaff = super.addObject(session, arg);
            controlStaff.setAreaPeriod(arg);
        }else
            controlStaff = (ControlStaff) super.getUser(session);

        return controlStaff;
    }

        @Override
    public ControlStaff updateObject(HttpSession session, Integer arg) {
        // Control Staff actualiza sus datos
        if (arg == null) return (ControlStaff) getUser(session);
        // Municipal manager actualiza los datos del control staff cuyo id es pasado por argumento
        else return this.controlStaffDao.getById(arg);
    }

    @Override
    public Map<String, Object> data(ControlStaff controlStaff) {
        AreaPeriod areaPeriod = this.areaPeriodDao.getById(controlStaff.getAreaPeriod());
        Area area = this.areaDao.getParentOf(areaPeriod);
        Municipality municipality = this.municipalityDao.getParentOf(area);
        return Map.of("municipality", municipalityDao.getParentOf(area), "area", area, "areaPeriod", areaPeriod);
    }

    @Override
    public String getRedirect(HttpSession session, Integer arg) {
        if (arg != null) return "../list/"+this.controlStaffDao.getById(arg).getAreaPeriod();
        else return "list";
    }
}
