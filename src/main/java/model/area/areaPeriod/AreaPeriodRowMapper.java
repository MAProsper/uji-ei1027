package model.area.areaPeriod;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaPeriodRowMapper implements RowMapper<AreaPeriod> {

    public AreaPeriod mapRow(ResultSet rs, int rowNum) throws SQLException {
        AreaPeriod areaPeriod = new AreaPeriod();
        areaPeriod.setId(rs.getInt("id"));
        areaPeriod.setArea(rs.getInt("area"));
        areaPeriod.setPeriod(rs.getInt("period"));
        return areaPeriod;
    }
}