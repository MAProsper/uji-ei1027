package app.dao.person;

import app.dao.Dao;
import app.model.person.Person;
import app.model.person.PersonResidence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PersonResidenceDao extends Dao<PersonResidence> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(PersonResidence personResidence) {
        jdbcTemplate.update("INSERT INTO PersonResidence VALUES(?, ?, ?)", personResidence.getId(), personResidence.getPerson(), personResidence.getAddress());
    }

    public void update(PersonResidence personResidence) {
        jdbcTemplate.update("UPDATE PersonResidence SET id =?, person =?, address =?", personResidence.getId(), personResidence.getPerson(), personResidence.getAddress());
    }

    public List<PersonResidence> getByPerson(Person person) {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonResidence WHERE person =?", new PersonResidenceRowMapper(), person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
