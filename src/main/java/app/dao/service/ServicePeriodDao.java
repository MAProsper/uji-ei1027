package app.dao.service;

import app.dao.Dao;
import app.model.service.Service;
import app.model.service.ServicePeriod;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServicePeriodDao extends Dao<ServicePeriod> {
    public ServicePeriodDao() {
        super(ServicePeriod.class);
    }

    public List<ServicePeriod> getByService(Service service) {
        try {
            return jdbc.query("SELECT * FROM ServicePeriod WHERE service =?", mapper, service.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ServicePeriod getByDate(LocalDateTime date) {
        try {
            return jdbc.queryForObject("SELECT * FROM ServicePeriod AS t1 JOIN Period AS p ON t1.period = p.id WHERE p.start <= =? AND COALESCE(p.end, =?) >= =?", mapper, date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}