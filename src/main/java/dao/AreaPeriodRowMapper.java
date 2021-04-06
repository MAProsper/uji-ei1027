package dao;

import model.AreaPeriod;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class AreaPeriodRowMapper implements RowMapper<AreaPeriod> {

    public AreaPeriod mapRow(ResultSet rs, int rowNum) throws SQLException {
        AreaPeriod areaPeriod = new AreaPeriod();
        areaPeriod.setId(rs.getInt("id"));
        areaPeriod.setArea(rs.getInt("area"));
        return areaPeriod;
    }
}