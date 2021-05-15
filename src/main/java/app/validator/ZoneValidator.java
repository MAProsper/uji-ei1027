package app.validator;

import app.model.ControlStaff;
import app.model.MunicipalManager;
import app.model.Zone;
import app.validator.generic.ValidatorV2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ZoneValidator extends ValidatorV2<Zone> {
    @Override
    public boolean list(HttpSession session, int arg) {
        return ifPerson(session, MunicipalManager.class);
    }
}
