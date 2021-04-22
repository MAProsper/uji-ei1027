package app.dao.person;

import app.dao.Dao;
import app.model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PersonDao extends Dao<Person> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?, ?)",
                person.getId(), person.getIdentification(), person.getName(), person.getUsername(), person.getPassword());
    }

    public void update(Person person) {
        jdbcTemplate.update("UPDATE Person SET id =?, identification =?, name =?, username =?, password =?",
                person.getId(), person.getIdentification(), person.getName(), person.getUsername(), person.getPassword());
    }

}
