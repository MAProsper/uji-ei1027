package app.dao.person;

import app.dao.Dao;
import app.model.person.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao extends Dao<Person> {
    public PersonDao() {
        super(Person.class);
    }
}
