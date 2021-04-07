package dao.person;

import model.PersonResidence;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonResidenceRowMapper implements RowMapper<PersonResidence> {

    public PersonResidence mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonResidence personResidence = new PersonResidence();
        personResidence.setId(rs.getInt("id"));
        personResidence.setPerson(rs.getInt("person"));
        personResidence.setAddress(rs.getInt("address"));
        return personResidence;
    }
}