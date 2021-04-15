package app.dao.person.personEmail;

import app.model.person.Person;
import app.model.person.PersonEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonEmailDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(PersonEmail personEmail) {
        jdbcTemplate.update("INSERT INTO PersonEmail VALUES(?, ?, ?)", personEmail.getId(), personEmail.getPerson(), personEmail.getEmail());
    }

    public void delete(PersonEmail personEmail) {
        jdbcTemplate.update("DELETE FROM PersonEmail  WHERE id = ?", personEmail.getId());
    }

    public void update(PersonEmail personEmail) {
        jdbcTemplate.update("UPDATE PersonEmail SET id =?, person =?, email =?", personEmail.getId(), personEmail.getPerson(), personEmail.getEmail());
    }

    public List<PersonEmail> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonEmail", new PersonEmailRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public PersonEmail getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PersonEmail WHERE id =?", new PersonEmailRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<PersonEmail> getByPerson(Person person) {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonEmail WHERE person =?", new PersonEmailRowMapper(), person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
