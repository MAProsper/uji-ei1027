package model.municipalManager.municipalManagerIsAssignedTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class MunicipalManagerIsAssignedToDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(MunicipalManagerIsAssignetTo municipalManagerIsAssignetTo) {
        jdbcTemplate.update("INSERT INTO MunicipalManagerIsAssignetTo VALUES(?, ?, ?, ?)",
                municipalManagerIsAssignetTo.getId(), municipalManagerIsAssignetTo.getMunicipality(), municipalManagerIsAssignetTo.getMunicipalManager(), municipalManagerIsAssignetTo.getPeriod());
    }

    public void update(MunicipalManagerIsAssignetTo municipalManagerIsAssignetTo) {
        jdbcTemplate.update("UPDATE MunicipalManagerIsAssignetTo SET id =?, municipality =?, municipal_manager =?, period =?",
                municipalManagerIsAssignetTo.getId(), municipalManagerIsAssignetTo.getMunicipality(), municipalManagerIsAssignetTo.getMunicipalManager(), municipalManagerIsAssignetTo.getPeriod());
    }

    public void delete(MunicipalManagerIsAssignetTo municipalManagerIsAssignetTo) {
        jdbcTemplate.update("DELETE FROM MunicipalManagerIsAssignedTo WHERE id =?",
                municipalManagerIsAssignetTo.getId());
    }

    public List<MunicipalManagerIsAssignetTo> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM MunicipalManagerIsAssignedTo", new MunicipalManagerIsAssignedToRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public MunicipalManagerIsAssignetTo get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM MunicipalManagerIsAssignedTo WHERE id =?", new MunicipalManagerIsAssignedToRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
