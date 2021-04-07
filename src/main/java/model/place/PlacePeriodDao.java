package model.place;

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
    private final String NOMBRE_TABLA = "PlacePeriod";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add (PlacePeriod object){
        jdbcTemplate.update("INSERT INTO " + this.NOMBRE_TABLA + " VALUES(?, ?, ?)", object.getId(), object.getPlace(), object.getPeriod() );
    }

    public void delete (PlacePeriod object){
        jdbcTemplate.update("DELETE FROM " + this.NOMBRE_TABLA + "  WHERE id = ?", object.getId() );
    }

    public void update (PlacePeriod object){
        jdbcTemplate.update("UPDATE " + this.NOMBRE_TABLA + " SET id =?", object.getId());
    }

    public List<PlacePeriod> get (PlacePeriod object){
        try {
            return jdbcTemplate.query("SELECT * FROM " +  this.NOMBRE_TABLA, new PlacePeriodRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
