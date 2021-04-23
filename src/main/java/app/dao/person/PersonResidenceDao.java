package app.dao.person;

import app.dao.Dao;
import app.model.person.Person;
import app.model.person.PersonResidence;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonResidenceDao extends Dao<PersonResidence> {
    public PersonResidenceDao() {
        super(PersonResidence.class);
    }

    public List<PersonResidence> getByPerson(Person person) {
        try {
            return jdbc.query("SELECT * FROM PersonResidence WHERE person =?", mapper, person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
