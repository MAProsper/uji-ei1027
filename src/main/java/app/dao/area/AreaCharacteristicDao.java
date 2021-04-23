package app.dao.area;

import app.dao.Dao;
import app.model.area.Area;
import app.model.area.AreaCharacteristic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AreaCharacteristicDao extends Dao<AreaCharacteristic> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(AreaCharacteristic areaCharacteristic) {
        jdbcTemplate.update("INSERT INTO AreaCharacteristic VALUES(?, ?, ?)", areaCharacteristic.getId(), areaCharacteristic.getArea(), areaCharacteristic.getCharacteristic());
    }

    public void update(AreaCharacteristic areaCharacteristic) {
        jdbcTemplate.update("UPDATE AreaCharacteristic SET id =?, area =?, characteristic =?", areaCharacteristic.getId(), areaCharacteristic.getArea(), areaCharacteristic.getCharacteristic());
    }

    public List<AreaCharacteristic> getByArea(Area area) {
        try {
            return jdbcTemplate.query("SELECT * FROM AreaCharacteristic WHERE area =?", new AreaCharacteristicRowMapper(), area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
