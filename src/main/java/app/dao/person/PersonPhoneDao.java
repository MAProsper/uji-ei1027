package app.dao.person;

import app.dao.Dao;
import app.model.person.Person;
import app.model.person.PersonPhone;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonPhoneDao extends Dao<PersonPhone> {
    public PersonPhoneDao() {
        super(PersonPhone.class);
    }

    public List<PersonPhone> getByPerson(Person person) {
        try {
            return jdbc.query("SELECT * FROM PersonPhone WHERE person =?", mapper, person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
