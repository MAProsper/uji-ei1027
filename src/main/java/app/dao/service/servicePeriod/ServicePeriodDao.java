package app.dao.service.servicePeriod;

import app.dao.Dao;
import app.model.service.Service;
import app.model.service.ServicePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ServicePeriodDao implements Dao<ServicePeriod> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(ServicePeriod object) {
        jdbcTemplate.update("INSERT INTO ServicePeriod VALUES(?, ?, ?)", object.getId(), object.getService(), object.getPeriod());
    }

    public void delete(ServicePeriod object) {
        jdbcTemplate.update("DELETE FROM ServicePeriod  WHERE id = ?", object.getId());
    }

    public void update(ServicePeriod object) {
        jdbcTemplate.update("UPDATE ServicePeriod SET id =?,  service =?, period =?", object.getId(), object.getService(), object.getPeriod());
    }

    public List<ServicePeriod> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM ServicePeriod", new ServicePeriodRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ServicePeriod getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ServicePeriod WHERE id =?", new ServicePeriodRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ServicePeriod> getByService(Service service) {
        try {
            return jdbcTemplate.query("SELECT * FROM ServicePeriod WHERE service =?", new ServicePeriodRowMapper(), service.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}