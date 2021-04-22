package app.dao.place;

import app.dao.Dao;
import app.model.place.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PlaceDao extends Dao<Place> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Place place) {
        jdbcTemplate.update("INSERT INTO Place VALUES(?, ?, ?)", place.getId(), place.getName(), place.getLocation());
    }

    public void update(Place place) {
        jdbcTemplate.update("UPDATE Place SET place =?, name =?, location =?", place.getId(), place.getName(), place.getLocation());
    }

}
