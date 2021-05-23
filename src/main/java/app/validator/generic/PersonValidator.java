package app.validator.generic;

import app.dao.generic.PersonDao;
import app.model.generic.Activeable;
import app.model.generic.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class PersonValidator<T extends Person> extends SignableValidator<T> {
    @Autowired List<PersonDao<?>> personDaos;

    @Override
    public void object(T object, FieldErrors errors) {
        super.object(object, errors);

        boolean duplicate = personDaos.stream().map(dao -> dao.getByIdentification(object.getIdentification())).anyMatch(p -> Activeable.isActive(p) && !p.equals(object));
        if (duplicate) errors.accept("identification", "El identificador ya esta en uso");

        if (!object.getMail().matches(".+@.+"))
            errors.accept("mail", "Formato de mail invalido");
    }
}
