package app.validator;

import app.model.Municipality;
import app.validator.generic.ValidatorV2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MunicipalityValidator extends ValidatorV2<Municipality> {
    @Override
    public boolean list(HttpSession session) {
        return true;
    }
}
