package dao;
import model.Citizen;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public final class CitizenRowMapper implements RowMapper<Citizen> {
    @Override
    public Citizen mapRow(ResultSet rs, int rowNum) throws SQLException {
        Citizen citizen = new Citizen();
        citizen.setPerson(rs.getInt("person"));
        return citizen;
    }
}
