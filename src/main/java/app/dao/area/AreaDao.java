package app.dao.area;

import app.dao.Dao;
import app.model.area.Area;
import app.model.municipality.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AreaDao implements Dao<Area> {
    private JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(Area area) {
        jdbcTemplate.update("INSERT INTO Area VALUES(?, ?, ?, ?, ?)", area.getPlace(), area.getMunicipality(), area.getDescription(), area.getLenght(), area.getWidth());
    }

    public void delete(Area area) {
        jdbcTemplate.update("DELETE FROM Area  WHERE place = ?", area.getPlace());
    }

    public void update(Area area) {
        jdbcTemplate.update("UPDATE Area SET place =?, municipality =?, description =?, length =?, width =?", area.getPlace(), area.getMunicipality(), area.getDescription(), area.getLenght(), area.getWidth());
    }

    public List<Area> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM Area", new AreaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Area getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Area WHERE place =?", new AreaRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Area> getByMunicipality(Municipality municipality) {
        try {
            return jdbcTemplate.query("SELECT * FROM Area WHERE municipality =?", new AreaRowMapper(), municipality.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}
