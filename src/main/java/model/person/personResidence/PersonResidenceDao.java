package model.person.personResidence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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

    public List<PersonResidence> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonResidence", new PersonResidenceRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
