package app.dao.place;

import app.model.place.PlacePeriod;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlacePeriodRowMapper implements RowMapper<PlacePeriod> {
    @Override
    public PlacePeriod mapRow(ResultSet rs, int i) throws SQLException {
        PlacePeriod placePeriod = new PlacePeriod();
        placePeriod.setId(rs.getInt("id"));
        placePeriod.setPlace(rs.getInt("place"));
        placePeriod.setPeriod(rs.getInt("period"));
        return placePeriod;
    }
}
