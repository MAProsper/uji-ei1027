package app.validator.generic;

import app.model.generic.Model;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;

public class Validator<T extends Model> implements org.springframework.validation.Validator {
    protected Class<T> cls;

    public Validator(Class<T> cls) {
        this.cls = cls;
    }

    @Override
    public boolean supports(@NonNull Class<?> cls) {
        return this.cls.isAssignableFrom(cls);
    }

    @Override
    public void validate(@NonNull Object object, @NonNull Errors errors) {
        validate(cls.cast(object), errors);
    }

    public void validate(T object, Errors errors) {
    }
}
