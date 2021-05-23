package app.validator.generic;

import app.dao.generic.PersonDao;
import app.model.generic.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class PersonValidator<T extends Person> extends SignableValidator<T> {
    @Autowired List<PersonDao<?>> personDaos;

    @Override
    public void object(T object, FieldErrors errors) {
        super.object(object, errors);

        boolean duplicate = personDaos.stream().flatMap(dao -> dao.getAll().stream()).filter(Person::isActive).anyMatch(p -> !p.equals(object) && p.getIdentification().equals(object.getIdentification()));
        if (duplicate) errors.accept("identification", "El identificador ya esta en uso");

        if (!object.getMail().matches(".+@.+"))
            errors.accept("mail", "Formato de mail invalido");
    }
}
