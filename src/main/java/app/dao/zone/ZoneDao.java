package app.dao.zone;

import app.dao.Dao;
import app.model.area.Area;
import app.model.zone.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ZoneDao implements Dao<Zone> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Zone zone) {
        jdbcTemplate.update("INSERT INTO Zone VALUES(?, ?)", zone.getPlace(), zone.getCapacity());
    }

    public void delete(Zone zone) {
        jdbcTemplate.update("DELETE FROM Zone  WHERE place = ?", zone.getPlace());
    }

    public void update(Zone zone) {
        jdbcTemplate.update("UPDATE Zone SET place =?, capacity =?", zone.getPlace(), zone.getCapacity());
    }

    public List<Zone> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM Zone", new ZoneRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Zone getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Zone WHERE id =?", new ZoneRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Zone> getByArea(Area area) {
        try {
            return jdbcTemplate.query("SELECT * FROM Zone WHERE area =?", new ZoneRowMapper(), area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}
