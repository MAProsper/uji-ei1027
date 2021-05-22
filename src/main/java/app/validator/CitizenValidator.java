package app.validator;

import app.model.Citizen;
import app.model.generic.Person;
import app.validator.generic.PersonValidator;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CitizenValidator extends PersonValidator<Citizen> {
    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg != null) return forbidden();
        return ifPerson(session, Citizen.class);
    }

    @Override
    public boolean delete(HttpSession session, Integer arg) {
        return list(session, arg);
    }
}
