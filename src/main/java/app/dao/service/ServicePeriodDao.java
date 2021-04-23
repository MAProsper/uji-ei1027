package app.dao.service;

import app.dao.Dao;
import app.model.service.Service;
import app.model.service.ServicePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ServicePeriodDao extends Dao<ServicePeriod> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(ServicePeriod object) {
        jdbcTemplate.update("INSERT INTO ServicePeriod VALUES(?, ?, ?)", object.getId(), object.getService(), object.getPeriod());
    }

    public void update(ServicePeriod object) {
        jdbcTemplate.update("UPDATE ServicePeriod SET id =?,  service =?, period =?", object.getId(), object.getService(), object.getPeriod());
    }

    public List<ServicePeriod> getByService(Service service) {
        try {
            return jdbcTemplate.query("SELECT * FROM ServicePeriod WHERE service =?", new ServicePeriodRowMapper(), service.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ServicePeriod getByDate(LocalDateTime date) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ServicePeriod AS t1 JOIN Period AS p ON t1.period = p.id WHERE p.start <= =? AND COALESCE(p.end, =?) >= =?", new ServicePeriodRowMapper(), date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}