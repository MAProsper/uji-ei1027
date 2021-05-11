package app.validator;

import app.model.Session;
import app.model.generic.Person;
import app.service.SessionService;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public class SessionValidator extends Validator<Session> {
    @Autowired protected SessionService service;

    public void validate(Session session, Errors errors) {
        if (!service.getTypes().contains(session.getType()))
            errors.rejectValue("type", "validator", "Tipo de usuario invalido");
        else {
            Person person = service.getPerson(session);
            if (person == null) errors.rejectValue("password", "validador", "Contraseña incorrecta");
        }
    }
}
