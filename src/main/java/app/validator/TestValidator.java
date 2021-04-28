package app.validator;

import app.model.ServiceType;
import app.validator.generic.Validator;
import org.springframework.stereotype.Service;

@Service
public class TestValidator extends Validator<ServiceType> {
    public TestValidator() {
        super(ServiceType.class);
    }
}
