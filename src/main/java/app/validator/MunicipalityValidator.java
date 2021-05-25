package app.validator;

import app.model.Citizen;
import app.model.EnviromentalManager;
import app.model.MunicipalManager;
import app.model.Municipality;
import app.validator.generic.PlaceValidator;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MunicipalityValidator extends PlaceValidator<Municipality> {
    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg != null) return forbidden();
        return getUser(session) == null || ifPerson(session, Citizen.class, MunicipalManager.class, EnviromentalManager.class);
    }

    @Override
    public boolean update(HttpSession session, Integer arg) {
        return super.ifPerson(session, MunicipalManager.class);
    }
}
