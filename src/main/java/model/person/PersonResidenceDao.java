package model.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class PersonResidenceDao {
    private JdbcTemplate jdbcTemplate;
    private final String NOMBRE_TABLA = "PersonResidence";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add (PersonResidence object){
        jdbcTemplate.update("INSERT INTO " + this.NOMBRE_TABLA + " VALUES(?, ?, ?)", object.getId(), object.getPerson(), object.getAddress());
    }

    public void delete (PersonResidence object){
        jdbcTemplate.update("DELETE FROM " + this.NOMBRE_TABLA + "  WHERE id = ?", object.getId() );
    }

    public void update (PersonResidence object){
        jdbcTemplate.update("UPDATE " + this.NOMBRE_TABLA + " SET id =?, person =?, address =?", object.getId(), object.getPerson(), object.getAddress());
    }

    public List<PersonResidence> get (PersonResidence object){
        try {
            return jdbcTemplate.query("SELECT * FROM " +  this.NOMBRE_TABLA, new PersonResidenceRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
