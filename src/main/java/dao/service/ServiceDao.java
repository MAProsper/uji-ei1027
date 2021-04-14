package dao.service;

import model.area.Area;
import model.service.Service;
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
}
