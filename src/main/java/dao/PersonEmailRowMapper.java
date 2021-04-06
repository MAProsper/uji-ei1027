package dao;

import model.PersonEmail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonEmailRowMapper implements RowMapper<PersonEmail> {
    public PersonEmail mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
