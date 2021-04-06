package dao;

import model.PersonPeriod;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class PersonPeriodRowMapper implements RowMapper<PersonPeriod> {
    public PersonPeriod mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonPeriod personPeriod = new PersonPeriod();
        personPeriod.setId(rs.getInt("id"));
        personPeriod.setPerson(rs.getInt("person"));
        return personPeriod;
    }
}