package app.validator;

import app.model.Municipality;
import app.validator.generic.Validator;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MunicipalityValidator extends Validator<Municipality> {
    @Override
    public boolean list(HttpSession session) {
        return true;
    }
}
