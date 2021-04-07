package model.service;

import model.municipality.Municipality;
import model.municipality.MunicipalityRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

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

    public List<Service> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM Service", new ServiceRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
