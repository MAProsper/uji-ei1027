package model.area;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaImageRowMapper implements RowMapper<AreaImage> {

    @Override
    public AreaImage mapRow(ResultSet rs, int i) throws SQLException {
        AreaImage areaImage = new AreaImage();
        areaImage.setId(rs.getInt("id"));
        areaImage.setArea(rs.getInt("area"));
        areaImage.setImage(rs.getString("image"));
        return areaImage;
    }
}
