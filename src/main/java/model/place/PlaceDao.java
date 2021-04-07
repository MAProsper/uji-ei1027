package model.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class PlaceDao {

    private JdbcTemplate jdbcTemplate;
    private final String NOMBRE_TABLA = "Place";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add (Place object){
        jdbcTemplate.update("INSERT INTO " + this.NOMBRE_TABLA + " VALUES(?, ?, ?)", object.getId(), object.getName(), object.getLocation() );
    }

    public void delete (Place object){
        jdbcTemplate.update("DELETE FROM " + this.NOMBRE_TABLA + "  WHERE id = ?", object.getId() );
    }

    public void update (Place object){
        jdbcTemplate.update("UPDATE " + this.NOMBRE_TABLA + " SET place =?, name =?, location =?", object.getId(), object.getName(), object.getLocation());
    }

    public List<Place> get (Place object){
        try {
            return jdbcTemplate.query("SELECT * FROM " +  this.NOMBRE_TABLA, new PlaceRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
