package model.zone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ZoneDao {

    private JdbcTemplate jdbcTemplate;
    private final String NOMBRE_TABLA = "Zone";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add (Zone object){
        jdbcTemplate.update("INSERT INTO " + this.NOMBRE_TABLA + " VALUES(?, ?)", object.getPlace(), object.getCapacity() );
    }

    public void delete (Zone object){
        jdbcTemplate.update("DELETE FROM " + this.NOMBRE_TABLA + "  WHERE place = ?", object.getPlace() );
    }

    public void update (Zone object){
        jdbcTemplate.update("UPDATE " + this.NOMBRE_TABLA + " SET place =?, capacity =?", object.getPlace(), object.getCapacity());
    }

    public List<Zone> get (Zone object){
        try {
            return jdbcTemplate.query("SELECT * FROM " +  this.NOMBRE_TABLA, new ZoneRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
