package app.dao.service.serviceType;

import app.dao.Dao;
import app.model.service.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ServiceTypeDao extends Dao<ServiceType> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(ServiceType serviceType) {
        jdbcTemplate.update("INSERT INTO ServiceType VALUES(?, ?, ?)",
                serviceType.getId(), serviceType.getType(), serviceType.getDescription());
    }

    public void update(ServiceType serviceType) {
        jdbcTemplate.update("UPDATE ServiceType SET id =?, type =?, description =?",
                serviceType.getId(), serviceType.getType(), serviceType.getDescription());
    }

}
