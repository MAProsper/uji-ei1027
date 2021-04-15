package app.dao.service;

import app.dao.Dao;
import app.model.area.Area;
import app.model.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ServiceDao implements Dao<Service> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Service service) {
        jdbcTemplate.update("INSERT INTO Service VALUES(?, ?, ?)",
                service.getId(), service.getServiceType(), service.getArea());
    }

    public void update(Service service) {
        jdbcTemplate.update("UPDATE Service SET id =?, service_type =?, area =?",
                service.getId(), service.getServiceType(), service.getArea());
    }

    public void delete(Service service) {
        jdbcTemplate.update("DELETE FROM Service WHERE id =?",
                service.getId());
    }

    public List<Service> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM Service", new ServiceRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Service getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Service WHERE id =?", new ServiceRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Service> getByArea(Area area) {
        try {
            return jdbcTemplate.query("SELECT * FROM Service WHERE area =?", new ServiceRowMapper(), area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}
