package app.validator.generic;

import app.ApplicationException;
import app.model.generic.Model;
import app.util.Parametrized;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpSession;

public abstract class ValidatorV2<T extends Model> extends Parametrized<T> implements org.springframework.validation.Validator {
    @Override
    public boolean supports(@NonNull Class<?> cls) {
        return getParametrizedType().isAssignableFrom(cls);
    }

    @Override
    public void validate(@NonNull Object object, @NonNull Errors errors) {
        data(getParametrizedType().cast(object), errors);
    }

    public boolean list(HttpSession session) {
        throw new ApplicationException("No existe la operacion que esta realizando");
    }

    public boolean list(HttpSession session, int arg) {
        throw new ApplicationException("No existe la operacion que esta realizando");
    }

    public boolean add(HttpSession session) {
        throw new ApplicationException("No existe la operacion que esta realizando");
    }

    public boolean add(HttpSession session, int arg) {
        throw new ApplicationException("No existe la operacion que esta realizando");
    }

    public boolean update(HttpSession session) {
        throw new ApplicationException("No existe la operacion que esta realizando");
    }

    public boolean update(HttpSession session, int arg) {
        throw new ApplicationException("No existe la operacion que esta realizando");
    }

    public boolean delete(HttpSession session) {
        throw new ApplicationException("No existe la operacion que esta realizando");
    }

    public boolean delete(HttpSession session, int arg) {
        throw new ApplicationException("No existe la operacion que esta realizando");
    }

    public void data(T object, Errors errors) {
    }
}
