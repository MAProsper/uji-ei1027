package model.service.servicePeriod;

import model.service.Service;
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

    public void add(ServicePeriod object) {
        jdbcTemplate.update("INSERT INTO " + this.NOMBRE_TABLA + " VALUES(?, ?, ?)", object.getId(), object.getService(), object.getPeriod());
    }

    public void delete(ServicePeriod object) {
        jdbcTemplate.update("DELETE FROM " + this.NOMBRE_TABLA + "  WHERE id = ?", object.getId());
    }

    public void update(ServicePeriod object) {
        jdbcTemplate.update("UPDATE " + this.NOMBRE_TABLA + " SET id =?,  service =?, period =?", object.getId(), object.getService(), object.getPeriod());
    }

    public List<ServicePeriod> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM " + this.NOMBRE_TABLA, new ServicePeriodRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ServicePeriod get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ServicePeriod WHERE id =?", new ServicePeriodRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ServicePeriod> get(Service service) {
        try {
            return jdbcTemplate.query("SELECT * FROM ServicePeriod WHERE service =?", new ServicePeriodRowMapper(), service.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}