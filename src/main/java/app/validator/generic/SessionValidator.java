package app.validator.generic;

import app.dao.generic.PersonDao;
import app.model.generic.Person;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public abstract class SessionValidator<T extends Person> {
    @Autowired protected PasswordEncryptor encryptor;
    @Autowired protected PersonDao<T> dao;

    public void validate(T credentials, Errors errors) {
        Person person = dao.getByIdentification(credentials.getIdentification());
        if (person == null || !encryptor.checkPassword(credentials.getPassword(), person.getPassword()))
            errors.rejectValue("password", "validador", "Contraseña incorrecta");
    }
}
