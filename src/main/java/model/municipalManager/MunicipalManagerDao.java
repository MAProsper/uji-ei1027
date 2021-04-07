package model.municipalManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MunicipalManagerDao {
    private JdbcTemplate jdbcTemplate;
    private final String NOMBRE_TABLA = "MunicipalManager";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add (MunicipalManager object){
        jdbcTemplate.update("INSERT INTO " + this.NOMBRE_TABLA + " VALUES(?)", object.getPerson() );
    }

    public void delete (MunicipalManager object){
        jdbcTemplate.update("DELETE FROM " + this.NOMBRE_TABLA + "  WHERE id = ?", object.getPerson() );
    }

    public void update (MunicipalManager object){
        jdbcTemplate.update("UPDATE " + this.NOMBRE_TABLA + " SET person =?", object.getPerson());
    }

    public List<MunicipalManager> get (MunicipalManager object){
        try {
            return jdbcTemplate.query("SELECT * FROM " +  this.NOMBRE_TABLA, new MunicipalManagerRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
