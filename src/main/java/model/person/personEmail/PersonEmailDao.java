package model.person.personEmail;

import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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

    public List<PersonEmail> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonEmail", new PersonEmailRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public PersonEmail get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PersonEmail WHERE id =?", new PersonEmailRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public PersonEmail get(Person person) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PersonEmail WHERE person =?", new PersonEmailRowMapper(), person.getId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
