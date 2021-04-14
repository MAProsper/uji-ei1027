package model.person.personPeriod;

import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonPeriodDao {
    private JdbcTemplate jdbcTemplate;

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

    public List<PersonPeriod> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonPeriod", new PersonPeriodRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<PersonPeriod> get(Person person) {
        try {
            return jdbcTemplate.query("SELECT * FROM PersonPeriod WHERE person =?", new PersonPeriodRowMapper(), person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }


    /**
     *  Devuelve la lista de elementos añadidos entre e
     *  @param dateTime
     *
     */
    public List<PersonPeriod> getByIncludedDate(LocalDateTime dateTime) {
        try {
            return jdbcTemplate.query(
                    "SELECT * " +
                        "FROM PersonPeriod as t1 JOIN Period as p " +
                        "ON t1.period = p.period " +
                        "WHERE p.start >= =? " +
                        "AND COALESCE(Period.end, =?) <= =?",
                    new PersonPeriodRowMapper(), dateTime);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
