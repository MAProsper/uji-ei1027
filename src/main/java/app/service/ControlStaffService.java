package app.service;

import app.dao.ControlStaffDao;
import app.model.ControlStaff;
import app.service.generic.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
public class ControlStaffService extends PersonService<ControlStaff> {
    @Autowired ControlStaffDao controlStaffDao;

    @Override
    public List<ControlStaff> listObjects(HttpSession session, Integer arg) {
        // Caso mostrar datos del control Staff
        if (arg == null) return Collections.singletonList((ControlStaff) super.getUser(session));
        // Caso mostrar todos los del areaPeriod (arg = areaPeriod)
        else return this.controlStaffDao.getByAreaPeriod(arg);
    }

    @Override
    public ControlStaff addObject(HttpSession session, Integer arg) {
        ControlStaff controlStaff = super.addObject(session, arg);
        controlStaff.setAreaPeriod(arg);
        return controlStaff;
    }

    @Override
    public ControlStaff updateObject(HttpSession session, Integer arg) {
        // Control Staff actualiza sus datos
        if (arg == null) return (ControlStaff) getUser(session);
        // Municipal manager actualiza los datos del control staff cuyo id es pasado por argumento
        else return this.controlStaffDao.getById(arg);
    }

}
