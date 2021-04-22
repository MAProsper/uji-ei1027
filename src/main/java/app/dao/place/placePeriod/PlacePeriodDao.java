package app.dao.place.placePeriod;

import app.dao.Dao;
import app.model.place.Place;
import app.model.place.PlacePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PlacePeriodDao extends Dao<PlacePeriod> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(PlacePeriod object) {
        jdbcTemplate.update("INSERT INTO PlacePeriod VALUES(?, ?, ?)", object.getId(), object.getPlace(), object.getPeriod());
    }

    public void update(PlacePeriod object) {
        jdbcTemplate.update("UPDATE PlacePeriod SET id =?, place =?, period =? ", object.getId(), object.getPlace(), object.getPeriod());
    }

    public List<PlacePeriod> getByPlace(Place place) {
        try {
            return jdbcTemplate.query("SELECT * FROM PlacePeriod WHERE place =?", new PlacePeriodRowMapper(), place.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public PlacePeriod getByDate(LocalDateTime date) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PlacePeriod AS t1 JOIN Period AS p ON t1.period = p.id WHERE p.start <= =? AND COALESCE(p.end, =?) >= =?", new PlacePeriodRowMapper(), date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
