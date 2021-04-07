package model.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class PlaceDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(Place place) {
        jdbcTemplate.update("INSERT INTO Place VALUES(?, ?, ?)", place.getId(), place.getName(), place.getLocation());
    }

    public void delete(Place place) {
        jdbcTemplate.update("DELETE FROM Place  WHERE id = ?", place.getId());
    }

    public void update(Place place) {
        jdbcTemplate.update("UPDATE Place SET place =?, name =?, location =?", place.getId(), place.getName(), place.getLocation());
    }

    public List<Place> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM Place", new PlaceRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Place get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Place WHERE id =?", new PlaceRowMapper(), id);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
}
