package dao;

import model.PersonPhone;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class PersonPhoneRowMapper implements RowMapper<PersonPhone> {

    public PersonPhone mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonPhone personPhone = new PersonPhone();
        personPhone.setId(rs.getInt("id"));
        personPhone.setPerson(rs.getInt("person"));
        personPhone.setPhone(rs.getString("phone"));
        return personPhone;
    }
}