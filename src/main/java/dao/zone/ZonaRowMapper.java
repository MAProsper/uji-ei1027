package dao.zone;

import model.Zone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZonaRowMapper implements RowMapper<Zone> {

    @Override
    public Zone mapRow(ResultSet rs, int i) throws SQLException {
        Zone zone = new Zone();
        zone.setPlace(rs.getInt("id"));
        return zone;
    }
}
