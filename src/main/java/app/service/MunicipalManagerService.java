package app.service;

import app.model.MunicipalManager;
import app.service.generic.PersonService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
public class MunicipalManagerService extends PersonService<MunicipalManager> {
    @Override
    public List<MunicipalManager> listObjects(HttpSession session, Integer arg) {
        return Collections.singletonList((MunicipalManager) getUser(session));
    }

    @Override
    public MunicipalManager updateObject(HttpSession session, Integer arg) {
        return (MunicipalManager) getUser(session);
    }

    @Override
    public void updateProcess(HttpSession session, Integer arg, MunicipalManager object) {
        super.updateProcess(session, arg, object);
        addSession(session, object);
    }
}
