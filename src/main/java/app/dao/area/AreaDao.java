package app.dao.area;

import app.dao.Dao;
import app.model.area.Area;
import app.model.municipality.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AreaDao extends Dao<Area> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Area area) {
        jdbcTemplate.update("INSERT INTO Area VALUES(?, ?, ?, ?, ?)", area.getPlace(), area.getMunicipality(), area.getDescription(), area.getLength(), area.getWidth());
    }

    public void update(Area area) {
        jdbcTemplate.update("UPDATE Area SET place =?, municipality =?, description =?, length =?, width =?", area.getPlace(), area.getMunicipality(), area.getDescription(), area.getLength(), area.getWidth());
    }

    public List<Area> getByMunicipality(Municipality municipality) {
        try {
            return jdbcTemplate.query("SELECT * FROM Area WHERE municipality =?", new AreaRowMapper(), municipality.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
