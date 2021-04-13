package model.zone;

import model.area.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ZoneDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(Zone zone) {
        jdbcTemplate.update("INSERT INTO Zone VALUES(?, ?)", zone.getPlace(), zone.getCapacity());
    }

    public void delete(Zone zone) {
        jdbcTemplate.update("DELETE FROM Zone  WHERE place = ?", zone.getPlace());
    }

    public void update(Zone zone) {
        jdbcTemplate.update("UPDATE Zone SET place =?, capacity =?", zone.getPlace(), zone.getCapacity());
    }

    public List<Zone> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM Zone", new ZoneRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Zone get(int place) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Zone WHERE id =?", new ZoneRowMapper(), place);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Zone> get(Area area) {
        try {
            return jdbcTemplate.query("SELECT * FROM Zone WHERE area =?", new ZoneRowMapper(), area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
