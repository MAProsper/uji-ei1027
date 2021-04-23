package app.dao.place;

import app.dao.Dao;
import app.model.place.Place;
import app.model.place.PlacePeriod;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlacePeriodDao extends Dao<PlacePeriod> {
    public PlacePeriodDao() {
        super(PlacePeriod.class);
    }

    public List<PlacePeriod> getByPlace(Place place) {
        try {
            return jdbc.query("SELECT * FROM PlacePeriod WHERE place =?", mapper, place.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public PlacePeriod getByDate(LocalDateTime date) {
        try {
            return jdbc.queryForObject("SELECT * FROM PlacePeriod AS t1 JOIN Period AS p ON t1.period = p.id WHERE p.start <= =? AND COALESCE(p.end, =?) >= =?", mapper, date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
