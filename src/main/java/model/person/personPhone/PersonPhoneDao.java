package model.person.personPhone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonPhoneDao {
    private JdbcTemplate jdbcTemplate;

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

    public List<PersonPhone> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonPhone", new PersonPhoneRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
