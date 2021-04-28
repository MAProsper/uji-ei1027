package app.validator.generic;

import app.model.generic.Model;
import app.util.Parametrized;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;

public abstract class Validator<T extends Model> extends Parametrized<T> implements org.springframework.validation.Validator {
    @Override
    public boolean supports(@NonNull Class<?> cls) {
        return getParametrizedType().isAssignableFrom(cls);
    }

    @Override
    public void validate(@NonNull Object object, @NonNull Errors errors) {
        validate(getParametrizedType().cast(object), errors);
    }

    public void validate(T object, Errors errors) {
    }
}
