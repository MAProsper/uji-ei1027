package app.service;

import app.model.Citizen;
import app.service.generic.PersonService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
public class CitizenService extends PersonService<Citizen> {
    @Override
    public List<Citizen> listObjects(HttpSession session, Integer arg) {
        return Collections.singletonList((Citizen) getUser(session));
    }

    @Override
    public Citizen updateObject(HttpSession session, Integer arg) {
        return (Citizen) getUser(session);
    }

    @Override
    public void addProcess(HttpSession session, Integer arg, Citizen object) {
        super.addProcess(session, arg, object);
        addSession(session, object);
    }

    @Override
    public void updateProcess(HttpSession session, Integer arg, Citizen object) {
        super.updateProcess(session, arg, object);
        addSession(session, object);
    }

    @Override
    public void deleteProcess(HttpSession session, Integer arg) {
        dao.delete(getUser(session).getId());
        deleteSession(session);
    }
}
