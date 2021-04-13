package model.area.areaCharacteristic;

import model.area.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class AreaCharacteristicDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(AreaCharacteristic areaCharacteristic) {
        jdbcTemplate.update("INSERT INTO AreaCharacteristic VALUES(?, ?, ?)", areaCharacteristic.getId(), areaCharacteristic.getArea(), areaCharacteristic.getCharacteristic());
    }

    public void delete(AreaCharacteristic areaCharacteristic) {
        jdbcTemplate.update("DELETE FROM AreaCharacteristic  WHERE id = ?", areaCharacteristic.getId());
    }

    public void update(AreaCharacteristic areaCharacteristic) {
        jdbcTemplate.update("UPDATE AreaCharacteristic SET id =?, are =?, characteristic =?", areaCharacteristic.getId(), areaCharacteristic.getArea(), areaCharacteristic.getCharacteristic());
    }

    public List<AreaCharacteristic> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM AreaCharacteristic", new AreaCharacteristicRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public AreaCharacteristic get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM AreaCharacteristic WHERE id =?", new AreaCharacteristicRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<AreaCharacteristic> get(Area area) {
        try {
            return jdbcTemplate.query("SELECT * FROM AreaCharacteristic WHERE area =?", new AreaCharacteristicRowMapper(), area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
