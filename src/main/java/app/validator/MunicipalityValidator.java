package app.validator;

import app.model.Municipality;
import app.validator.generic.PlaceValidator;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MunicipalityValidator extends PlaceValidator<Municipality> {
    @Override
    public boolean list(HttpSession session, Integer arg) {
        if (arg != null) return forbidden();
        return true;
    }
}
