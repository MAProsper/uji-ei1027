package model.service.serviceType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ServiceTypeDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(ServiceType serviceType) {
        jdbcTemplate.update("INSERT INTO ServiceType VALUES(?, ?, ?)",
                serviceType.getId(), serviceType.getType(), serviceType.getDescription());
    }

    public void update(ServiceType serviceType) {
        jdbcTemplate.update("UPDATE ServiceType SET id =?, type =?, description =?",
                serviceType.getId(), serviceType.getType(), serviceType.getDescription());
    }

    public void delete(ServiceType serviceType) {
        jdbcTemplate.update("DELETE FROM ServiceType WHERE id =?",
                serviceType.getId());
    }

    public List<ServiceType> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM ServiceType", new ServiceTypeRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ServiceType get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ServiceType WHERE id =?", new ServiceTypeRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
