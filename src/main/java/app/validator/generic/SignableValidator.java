package app.validator.generic;

import app.model.generic.Signable;
import org.springframework.validation.Errors;

public abstract class SignableValidator<T extends Signable> extends Validator<T> {
    @Override
    public void validate(T object, Errors errors) {
        super.validate(object, errors);

        if (object.getSingDown() != null && object.getSingUp().compareTo(object.getSingDown()) > 0)
            errors.rejectValue("La fecha de baja deber ser posterior a la de alta", "signOrder");
    }
}
