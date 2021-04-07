package dao.municipality;

import model.Municipality;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MunicipalityRowMapper implements RowMapper<Municipality> {
    @Override
    public Municipality mapRow(ResultSet rs, int i) throws SQLException {
        Municipality municipality = new Municipality();
        municipality.setPlace(rs.getInt("place"));
        return municipality;
    }
}
