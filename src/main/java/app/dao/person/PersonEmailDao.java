package app.dao.person;

import app.dao.Dao;
import app.model.person.Person;
import app.model.person.PersonEmail;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonEmailDao extends Dao<PersonEmail> {
    public PersonEmailDao() {
        super(PersonEmail.class);
    }

    public List<PersonEmail> getByPerson(Person person) {
        try {
            return jdbc.query("SELECT * FROM PersonEmail WHERE person =?", mapper, person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
