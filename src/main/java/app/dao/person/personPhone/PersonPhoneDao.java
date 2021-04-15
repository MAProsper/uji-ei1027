package app.dao.person.personPhone;

import app.dao.Dao;
import app.model.person.Person;
import app.model.person.PersonPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PersonPhoneDao implements Dao<PersonPhone> {
    private JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(PersonPhone personPhone) {
        jdbcTemplate.update("INSERT INTO PersonPhone VALUES(?, ?, ?)",
                personPhone.getId(), personPhone.getPerson(), personPhone.getPhone());
    }

    public void update(PersonPhone personPhone) {
        jdbcTemplate.update("UPDATE PersonPhone SET id =?, person =?, phone =?",
                personPhone.getId(), personPhone.getPerson(), personPhone.getPhone());
    }

    public void delete(PersonPhone personPhone) {
        jdbcTemplate.update("DELETE FROM PersonPhone WHERE id =?",
                personPhone.getId());
    }

    public List<PersonPhone> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonPhone", new PersonPhoneRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public PersonPhone getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PersonPhone WHERE id =?", new PersonPhoneRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<PersonPhone> getByPerson(Person person) {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonPhone WHERE person =?", new PersonPhoneRowMapper(), person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}
