package dao.person.personPhone;

import model.person.PersonPhone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonPhoneRowMapper implements RowMapper<PersonPhone> {

    public PersonPhone mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonPhone personPhone = new PersonPhone();
        personPhone.setId(rs.getInt("id"));
        personPhone.setPerson(rs.getInt("person"));
        personPhone.setPhone(rs.getString("phone"));
        return personPhone;
    }
}