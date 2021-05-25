package app.validator;

import app.model.MunicipalManager;
import app.validator.generic.PersonValidator;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MunicipalManagerValidator extends PersonValidator<MunicipalManager> {
    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg != null) return forbidden(); // No implementamos EnviromentalManager (ver managers de municipio)
        return ifPerson(session, MunicipalManager.class);
    }

    @Override
    public boolean update(HttpSession session, Integer arg) {
        return list(session, arg);
    }
}
