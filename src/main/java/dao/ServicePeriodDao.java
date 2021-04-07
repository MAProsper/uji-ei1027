package dao;

import model.ServicePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServicePeriodDao {

    private JdbcTemplate jdbcTemplate;
    private final String NOMBRE_TABLA = "ServicePeriod";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add (ServicePeriod object){
        jdbcTemplate.update("INSERT INTO " + this.NOMBRE_TABLA + " VALUES(?, ?, ?)", object.getId(), object.getService(), object.getPeriod() );
    }

    public void delete (ServicePeriod object){
        jdbcTemplate.update("DELETE FROM " + this.NOMBRE_TABLA + "  WHERE id = ?", object.getId() );
    }

    public void update (ServicePeriod object){
        jdbcTemplate.update("UPDATE " + this.NOMBRE_TABLA + " SET id =?,  service =?, period =?", object.getId(), object.getService(), object. getPeriod());
    }

    public List<ServicePeriod> get (ServicePeriod object){
        try {
            return jdbcTemplate.query("SELECT * FROM " +  this.NOMBRE_TABLA, new ServicePeriodRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}

/*


    public void add() {
        jdbcTemplate.update("INSERT INTO  VALUES(?, ?, ?, ?)",
            classificacio.getNomNadador(), classificacio.getNomProva(), classificacio.getPosicio(), classificacio.getTemps());
    }

    public void delete() {
        jdbcTemplate.update("DELETE from  where nom_nadador=? AND nom_prova=?",
                nomNadador, nomProva);
    }
    public void deleteClassificacio(Classificacio classificacio) {
        jdbcTemplate.update("DELETE from Prova where nom_nadador=? AND nom_prova=?",
                classificacio.getNomNadador(), classificacio.getNomProva());
    }

    public void updateClassificacio(Classificacio classificacio) {
        jdbcTemplate.update("UPDATE Classificacio SET posicio=?, temps=? where nom_nadador=? and nom_prova=?",
                classificacio.getPosicio(), classificacio.getTemps(), classificacio.getNomNadador(), classificacio.getNomProva());
    }

    public Classificacio getClassificacio(String nomNadador, String nomProva) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from Classificacio WHERE nom_nadador=? and nom_prova=?",
                    new ClassificacioRowMapper(), nomNadador, nomProva);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Classificacio> getClassificacions() {
        try {
            return jdbcTemplate.query("SELECT * from Classificacio",
                    new ClassificacioRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Classificacio>();
        }
    }
 */