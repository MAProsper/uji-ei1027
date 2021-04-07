package model.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class AreaCharacteristicDao {
    private JdbcTemplate jdbcTemplate;
    private final String NOMBRE_TABLA = "AreaCharacteristic";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add (AreaCharacteristic object){
        jdbcTemplate.update("INSERT INTO " + this.NOMBRE_TABLA + " VALUES(?, ?, ?)", object.getId(), object.getArea(), object.getCharacteristic() );
    }

    public void delete (AreaCharacteristic object){
        jdbcTemplate.update("DELETE FROM " + this.NOMBRE_TABLA + "  WHERE id = ?", object.getId() );
    }

    public void update (AreaCharacteristic object){
        jdbcTemplate.update("UPDATE " + this.NOMBRE_TABLA + " SET id =?, are =?, characteristic =?", object.getId(), object.getArea(), object.getCharacteristic() );
    }

    public List<AreaCharacteristic> get (AreaCharacteristic object){
        try {
            return jdbcTemplate.query("SELECT * FROM " +  this.NOMBRE_TABLA, new AreaCharacteristicRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
