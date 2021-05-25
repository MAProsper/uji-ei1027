package app.service;

import app.model.Citizen;
import app.model.ControlStaff;
import app.service.generic.PersonService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
public class ControlStaffService extends PersonService<ControlStaff> {

    @Override
    public List<ControlStaff> listObjects(HttpSession session, Integer arg) {
        return Collections.singletonList((ControlStaff) getUser(session));
    }

    @Override
    public ControlStaff updateObject(HttpSession session, Integer arg) {
        return (ControlStaff) getUser(session);
    }

    @Override
    public void addProcess(HttpSession session, Integer arg, ControlStaff object) {
        super.addProcess(session, arg, object);
        addSession(session, object);
    }

    @Override
    public void updateProcess(HttpSession session, Integer arg, ControlStaff object) {
        super.updateProcess(session, arg, object);
        addSession(session, object);
    }

    @Override
    public void deleteProcess(HttpSession session, Integer arg) {
        dao.delete(getUser(session).getId());
        deleteSession(session);
    }

}
