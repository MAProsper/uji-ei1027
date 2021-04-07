package model.citizen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class CitizenDao {

    private JdbcTemplate jdbcTemplate;
    private final String NOMBRE_TABLA = "Citizen";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add (Citizen object){
        jdbcTemplate.update("INSERT INTO " + this.NOMBRE_TABLA + " VALUES(?)", object.getPerson());
    }

    public void delete (Citizen object){
        jdbcTemplate.update("DELETE FROM " + this.NOMBRE_TABLA + "  WHERE person = ?", object.getPerson() );
    }

    public void update (Citizen object){
        jdbcTemplate.update("UPDATE " + this.NOMBRE_TABLA + " SET person =?", object.getPerson());
    }

    public List<Citizen> get (Citizen object){
        try {
            return jdbcTemplate.query("SELECT * FROM " +  this.NOMBRE_TABLA, new CitizenRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
