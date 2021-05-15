package app.validator;

import app.model.MunicipalManager;
import app.model.Zone;
import app.validator.generic.Validator;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ZoneValidator extends Validator<Zone> {
    @Override
    public boolean list(HttpSession session, int arg) {
        return ifPerson(session, MunicipalManager.class);
    }
}
