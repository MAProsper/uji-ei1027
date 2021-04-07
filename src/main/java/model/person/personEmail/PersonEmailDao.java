package model.person.personEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class PersonEmailDao {
    private JdbcTemplate jdbcTemplate;
    private final String NOMBRE_TABLA = "PersonEmail";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add (PersonEmail object){
        jdbcTemplate.update("INSERT INTO " + this.NOMBRE_TABLA + " VALUES(?, ?, ?)", object.getId(), object.getPerson(), object.getEmail());
    }

    public void delete (PersonEmail object){
        jdbcTemplate.update("DELETE FROM " + this.NOMBRE_TABLA + "  WHERE id = ?", object.getId() );
    }

    public void update (PersonEmail object){
        jdbcTemplate.update("UPDATE " + this.NOMBRE_TABLA + " SET id =?, person =?, email =?", object.getId(), object.getPerson(), object.getEmail());
    }

    public List<PersonEmail> get (PersonEmail object){
        try {
            return jdbcTemplate.query("SELECT * FROM " +  this.NOMBRE_TABLA, new PersonEmailRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
