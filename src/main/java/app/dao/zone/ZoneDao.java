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
public class ZoneDao extends Dao<Zone> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Zone zone) {
        jdbcTemplate.update("INSERT INTO Zone VALUES(?, ?)", zone.getPlace(), zone.getCapacity());
    }

    public void update(Zone zone) {
        jdbcTemplate.update("UPDATE Zone SET place =?, capacity =?", zone.getPlace(), zone.getCapacity());
    }

    public List<Zone> getByArea(Area area) {
        try {
            return jdbcTemplate.query("SELECT * FROM Zone WHERE area =?", new ZoneRowMapper(), area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
