package app.validator;

import app.model.Session;
import app.service.SessionService;
import app.validator.generic.FieldError;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionValidator extends Validator<Session> {
    @Autowired protected SessionService service;

    @Override
    public boolean add(HttpSession session, Integer arg) {
        return arg != null ? forbidden() : !ifPerson(session);
    }

    @Override
    public boolean delete(HttpSession session, Integer arg) {
        return arg != null ? forbidden() : ifPerson(session);
    }

    @Override
    public void object(Session session, FieldError error) {
        if (service.getUser(session) == null)
            error.accept("password", "Contraseña incorrecta");
    }
}
