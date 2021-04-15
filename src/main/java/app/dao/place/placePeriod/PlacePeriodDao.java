package app.dao.place.placePeriod;

import app.model.place.Place;
import app.model.place.PlacePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlacePeriodDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(PlacePeriod object) {
        jdbcTemplate.update("INSERT INTO PlacePeriod VALUES(?, ?, ?)", object.getId(), object.getPlace(), object.getPeriod());
    }

    public void delete(PlacePeriod object) {
        jdbcTemplate.update("DELETE FROM PlacePeriod  WHERE id = ?", object.getId());
    }

    public void update(PlacePeriod object) {
        jdbcTemplate.update("UPDATE PlacePeriod SET id =?, place =?, period =? ", object.getId(), object.getPlace(), object.getPeriod());
    }

    public List<PlacePeriod> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM PlacePeriod", new PlacePeriodRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public PlacePeriod getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PlacePeriod WHERE id =?", new PlacePeriodRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<PlacePeriod> getByPlace(Place place) {
        try {
            return jdbcTemplate.query("SELECT * FROM PlacePeriod WHERE place =?", new PlacePeriodRowMapper(), place.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }


}
