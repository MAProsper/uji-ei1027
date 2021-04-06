package dao;

import model.AreaCharacteristic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class AreaCharacteristicRowMapper implements RowMapper<AreaCharacteristic> {

    public AreaCharacteristic mapRow(ResultSet rs, int rowNum) throws SQLException {
        AreaCharacteristic areaCharacteristics = new AreaCharacteristic();
        areaCharacteristics.setId(rs.getInt("id"));
        areaCharacteristics.setArea(rs.getInt("area"));
        return areaCharacteristics;
    }
}