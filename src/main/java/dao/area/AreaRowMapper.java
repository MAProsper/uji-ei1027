package dao.area;

import model.area.Area;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaRowMapper implements RowMapper<Area> {

    @Override
    public Area mapRow(ResultSet rs, int i) throws SQLException {
        Area area = new Area();
        area.setPlace(rs.getInt("place"));
        area.setMunicipality(rs.getInt("municipaity"));
        area.setDescription(rs.getString("description"));
        area.setLenght(rs.getDouble("length"));
        area.setWidth(rs.getDouble("width"));
        return area;
    }
}