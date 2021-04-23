package app.dao.person;

import app.dao.Dao;
import app.model.person.Person;
import app.model.person.PersonEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PersonEmailDao extends Dao<PersonEmail> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(PersonEmail personEmail) {
        jdbcTemplate.update("INSERT INTO PersonEmail VALUES(?, ?, ?)", personEmail.getId(), personEmail.getPerson(), personEmail.getEmail());
    }

    public void update(PersonEmail personEmail) {
        jdbcTemplate.update("UPDATE PersonEmail SET id =?, person =?, email =?", personEmail.getId(), personEmail.getPerson(), personEmail.getEmail());
    }

    public List<PersonEmail> getByPerson(Person person) {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonEmail WHERE person =?", new PersonEmailRowMapper(), person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
