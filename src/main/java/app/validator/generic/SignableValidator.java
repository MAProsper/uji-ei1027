package app.validator.generic;

import app.model.generic.Signable;

public abstract class SignableValidator<T extends Signable> extends Validator<T> {
    @Override
    public void object(T object, FieldErrors errors) {
        super.object(object, errors);

        if (object.getSingUp() == null) {
            errors.accept("signUp", "Formato de dado de alta invalido");
        } else if (object.getSingDown() != null && object.getSingUp().compareTo(object.getSingDown()) >= 0)
            errors.accept("signDown", "La fecha de baja deber ser posterior a la de alta");
    }
}
