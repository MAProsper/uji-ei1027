package app.dao.person.personResidence;

import app.model.person.Person;
import app.model.person.PersonResidence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonResidenceDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(PersonResidence personResidence) {
        jdbcTemplate.update("INSERT INTO PersonResidence VALUES(?, ?, ?)", personResidence.getId(), personResidence.getPerson(), personResidence.getAddress());
    }

    public void delete(PersonResidence personResidence) {
        jdbcTemplate.update("DELETE FROM PersonResidence  WHERE id = ?", personResidence.getId());
    }

    public void update(PersonResidence personResidence) {
        jdbcTemplate.update("UPDATE PersonResidence SET id =?, person =?, address =?", personResidence.getId(), personResidence.getPerson(), personResidence.getAddress());
    }

    public List<PersonResidence> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonResidence", new PersonResidenceRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public PersonResidence getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PersonResidence WHERE id =?", new PersonResidenceRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<PersonResidence> getByPerson(Person person) {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonResidence WHERE person =?", new PersonResidenceRowMapper(), person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
