package app.validator.generic;

import app.ApplicationException;
import app.model.generic.Model;
import app.model.generic.Person;
import app.util.Parametrized;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

public abstract class Validator<T extends Model> extends Parametrized<T> implements org.springframework.validation.Validator {
    @Override
    public boolean supports(@NonNull Class<?> cls) {
        return getParametrizedType().isAssignableFrom(cls);
    }

    @Override
    public void validate(@NonNull Object object, @NonNull Errors errors) {
        object(getParametrizedType().cast(object), (field, reason) -> errors.rejectValue(field, "validator", reason));
    }

    public boolean list(HttpSession session, Integer arg) {
        return forbidden();
    }

    public boolean add(HttpSession session, Integer arg) {
        return forbidden();
    }

    public boolean update(HttpSession session, Integer arg) {
        return forbidden();
    }

    public boolean delete(HttpSession session, Integer arg) {
        return forbidden();
    }

    public void object(T object, FieldError error) {
    }

    protected boolean forbidden() {
        throw new ApplicationException("La operacion que ha intentado realizar no esta permitida");
    }

    @SafeVarargs
    protected final boolean ifPerson(HttpSession session, Class<? extends Person>... persons) {
        Person person = (Person) session.getAttribute("user");
        if (person == null) return false;
        else if (Arrays.stream(persons).anyMatch(cls -> cls.isInstance(person))) return true;
        else return forbidden();
    }

    protected final boolean ifPerson(HttpSession session) {
        return ifPerson(session, Person.class);
    }
}
