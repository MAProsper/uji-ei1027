package app.dao.person;

import app.dao.Dao;
import app.model.person.Person;
import app.model.person.PersonPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PersonPhoneDao extends Dao<PersonPhone> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(PersonPhone personPhone) {
        jdbcTemplate.update("INSERT INTO PersonPhone VALUES(?, ?, ?)",
                personPhone.getId(), personPhone.getPerson(), personPhone.getPhone());
    }

    public void update(PersonPhone personPhone) {
        jdbcTemplate.update("UPDATE PersonPhone SET id =?, person =?, phone =?",
                personPhone.getId(), personPhone.getPerson(), personPhone.getPhone());
    }

    public List<PersonPhone> getByPerson(Person person) {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonPhone WHERE person =?", new PersonPhoneRowMapper(), person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
