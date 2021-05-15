package app.validator;

import app.model.Session;
import app.service.SessionService;
import app.validator.generic.FieldError;
import app.validator.generic.ValidatorV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionValidator extends ValidatorV2<Session> {
    @Autowired protected SessionService service;

    @Override
    public boolean add(HttpSession session) {
        return !ifPerson(session);
    }

    @Override
    public boolean delete(HttpSession session) {
        return ifPerson(session);
    }

    @Override
    public void object(Session session, FieldError error) {
        if (service.getUser(session) == null)
            error.accept("password", "Contraseña incorrecta");
    }
}
