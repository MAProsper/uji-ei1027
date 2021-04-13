package model.area;

import model.municipality.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class AreaDao {

    private JdbcTemplate jdbcTemplate;

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

    public List<Area> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM Area", new AreaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Area get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Area WHERE id =?", new AreaRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Area> get(Municipality municipality) {
        try {
            return jdbcTemplate.query("SELECT * FROM Area WHERE municipality =?", new AreaRowMapper(), municipality.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
