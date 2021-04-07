package model.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class AreaDao {

    private JdbcTemplate jdbcTemplate;
    private final String NOMBRE_TABLA = "Area";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add (Area object){
        jdbcTemplate.update("INSERT INTO " + this.NOMBRE_TABLA + " VALUES(?, ?, ?, ?, ?)", object.getPlace(), object.getMunicipality(), object.getDescription(), object.getLenght(), object.getWidth());
    }

    public void delete (Area object){
        jdbcTemplate.update("DELETE FROM " + this.NOMBRE_TABLA + "  WHERE place = ?", object.getPlace() );
    }

    public void update (Area object){
        jdbcTemplate.update("UPDATE " + this.NOMBRE_TABLA + " SET place =?, municipality =?, description =?, length =?, width =?", object.getPlace(), object.getMunicipality(), object.getDescription(), object.getLenght(), object.getWidth());
    }

    public List<Area> get (Area object){
        try {
            return jdbcTemplate.query("SELECT * FROM " +  this.NOMBRE_TABLA, new AreaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
