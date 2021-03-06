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

    /**
     * Verificar si el usuario tiene permisos
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @return true (tiene permisos), false (iniciar sesion), forbidden (peticion inválida)
     */
    public boolean list(HttpSession session, Integer arg) {
        return forbidden();
    }

    /**
     * Verificar si el usuario tiene permisos
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @return true (tiene permisos), false (iniciar sesion), forbidden (peticion inválida)
     */
    public boolean add(HttpSession session, Integer arg) {
        return forbidden();
    }

    /**
     * Verificar si el usuario tiene permisos
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @return true (tiene permisos), false (iniciar sesion), forbidden (peticion inválida)
     */
    public boolean update(HttpSession session, Integer arg) {
        return forbidden();
    }

    /**
     * Verificar si el usuario tiene permisos
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @return true (tiene permisos), false (iniciar sesion), forbidden (peticion inválida)
     */
    public boolean delete(HttpSession session, Integer arg) {
        return forbidden();
    }

    /**
     * Verificar si el objeto es valido
     *
     * @param object objecto referencia
     * @param errors errors.accept(field, error)
     */
    public void object(T object, FieldErrors errors) {
    }

    /**
     * La peticion es inválida y iniciar sesion no lo solucionara
     *
     * @return peticion inválida
     */
    protected boolean forbidden() {
        throw new ApplicationException("La operacion que ha intentado realizar no esta permitida");
    }

    /**
     * Verificar si un tipo de usuario ha inciado sesion
     *
     * @param session sesion
     * @param persons tipos de persona
     * @return verificacion
     */
    @SafeVarargs
    protected final boolean ifPerson(HttpSession session, Class<? extends Person>... persons) {
        Person person = getUser(session);
        if (person == null) return false;
        return Arrays.stream(persons).anyMatch(cls -> cls.isInstance(person));
    }

    /**
     * Verificar si un algun usuario ha inciado sesion
     *
     * @param session sesion
     * @return verificacion
     */
    protected final boolean ifPerson(HttpSession session) {
        return ifPerson(session, Person.class);
    }

    /**
     * Obtener el usuario de la sesion
     *
     * @param session sesion
     * @return usuario
     */
    protected Person getUser(HttpSession session) {
        return (Person) session.getAttribute("user");
    }
}
