package app.dao;

import app.dao.generic.Dao;
import app.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao extends Dao<Person> {
    public PersonDao() {
        super(Person.class);
    }
}
