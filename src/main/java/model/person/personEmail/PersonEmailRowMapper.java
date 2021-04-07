package model.person.personEmail;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonEmailRowMapper implements RowMapper<PersonEmail> {
    public PersonEmail mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonEmail personEmail = new PersonEmail();
        personEmail.setId(rs.getInt("id"));
        personEmail.setPerson(rs.getInt("person"));
        personEmail.setEmail(rs.getString("email"));
        return personEmail;
    }
}
