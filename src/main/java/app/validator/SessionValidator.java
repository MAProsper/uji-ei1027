package app.validator;

import app.model.Session;
import app.service.SessionService;
import app.validator.generic.FieldErrors;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionValidator extends Validator<Session> {
    @Autowired protected SessionService sessionService;

    @Override
    public boolean add(HttpSession session, Integer arg) {
        if (arg != null) return forbidden();
        return !ifPerson(session);
    }

    @Override
    public boolean delete(HttpSession session, Integer arg) {
        if (arg != null) return forbidden();
        return ifPerson(session);
    }

    @Override
    public void object(Session session, FieldErrors errors) {
        if (sessionService.getUser(session) == null)
            errors.accept("password", "Contraseña incorrecta");
    }
}
