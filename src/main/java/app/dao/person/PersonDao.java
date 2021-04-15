package app.dao.person;

import app.model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?, ?)",
                person.getId(), person.getIdentification(), person.getName(), person.getUsername(), person.getPassword());
    }

    public void update(Person person) {
        jdbcTemplate.update("UPDATE Person SET id =?, identification =?, name =?, username =?, password =?",
                person.getId(), person.getIdentification(), person.getName(), person.getUsername(), person.getPassword());
    }

    public void delete(Person person) {
        jdbcTemplate.update("DELETE FROM Person WHERE id =?",
                person.getId());
    }

    public List<Person> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM Person", new PersonRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Person getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Person WHERE id =?", new PersonRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
