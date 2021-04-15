package app.dao.person.personPeriod;

import app.dao.Dao;
import app.model.person.Person;
import app.model.person.PersonPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PersonPeriodDao implements Dao<PersonPeriod> {
    private JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(PersonPeriod personPeriod) {
        jdbcTemplate.update("INSERT INTO PersonPeriod VALUES(?, ?, ?)",
                personPeriod.getId(), personPeriod.getPerson(), personPeriod.getPeriod());
    }

    public void update(PersonPeriod personPeriod) {
        jdbcTemplate.update("UPDATE PersonPeriod SET id =?, person =?, period =?",
                personPeriod.getId(), personPeriod.getPerson(), personPeriod.getPeriod());
    }

    public void delete(PersonPeriod personPeriod) {
        jdbcTemplate.update("DELETE FROM PersonPeriod WHERE id =?",
                personPeriod.getId());
    }

    public List<PersonPeriod> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonPeriod", new PersonPeriodRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public PersonPeriod getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PersonPeriod WHERE id =?", new PersonPeriodRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<PersonPeriod> getByPerson(Person person) {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonPeriod WHERE person =?", new PersonPeriodRowMapper(), person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public PersonPeriod getByDate(LocalDateTime date) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PersonPeriod JOIN Period ON PersonPeriod.period = Period.id WHERE Period.start <= =? AND COALESCE(Period.end, =?) >= =?", new PersonPeriodRowMapper(), date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}
