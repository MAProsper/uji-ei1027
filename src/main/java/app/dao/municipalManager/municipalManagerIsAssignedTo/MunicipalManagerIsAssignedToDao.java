package app.dao.municipalManager.municipalManagerIsAssignedTo;

import app.dao.Dao;
import app.model.municipalManager.MunicipalManager;
import app.model.municipalManager.MunicipalManagerIsAssignedTo;
import app.model.municipality.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class MunicipalManagerIsAssignedToDao implements Dao<MunicipalManagerIsAssignedTo> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(MunicipalManagerIsAssignedTo municipalManagerIsAssignedTo) {
        jdbcTemplate.update("INSERT INTO MunicipalManagerIsAssignedTo VALUES(?, ?, ?, ?)",
                municipalManagerIsAssignedTo.getId(), municipalManagerIsAssignedTo.getMunicipality(), municipalManagerIsAssignedTo.getMunicipalManager(), municipalManagerIsAssignedTo.getPeriod());
    }

    public void update(MunicipalManagerIsAssignedTo municipalManagerIsAssignedTo) {
        jdbcTemplate.update("UPDATE MunicipalManagerIsAssignedTo SET id =?, municipality =?, municipal_manager =?, period =?",
                municipalManagerIsAssignedTo.getId(), municipalManagerIsAssignedTo.getMunicipality(), municipalManagerIsAssignedTo.getMunicipalManager(), municipalManagerIsAssignedTo.getPeriod());
    }

    public void delete(MunicipalManagerIsAssignedTo municipalManagerIsAssignedTo) {
        jdbcTemplate.update("DELETE FROM MunicipalManagerIsAssignedTo WHERE id =?",
                municipalManagerIsAssignedTo.getId());
    }

    public List<MunicipalManagerIsAssignedTo> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM MunicipalManagerIsAssignedTo", new MunicipalManagerIsAssignedToRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public MunicipalManagerIsAssignedTo getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM MunicipalManagerIsAssignedTo WHERE id =?", new MunicipalManagerIsAssignedToRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<MunicipalManagerIsAssignedTo> getByMunicipalManager(MunicipalManager municipalManager) {
        try {
            return jdbcTemplate.query("SELECT * FROM MunicipalManagerIsAssignedTo WHERE municipal_manager =?", new MunicipalManagerIsAssignedToRowMapper(), municipalManager.getPerson());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<MunicipalManagerIsAssignedTo> getByMunicipality(Municipality municipality) {
        try {
            return jdbcTemplate.query("SELECT * FROM MunicipalManagerIsAssignedTo WHERE municipality =?", new MunicipalManagerIsAssignedToRowMapper(), municipality.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public MunicipalManagerIsAssignedTo getByDate(LocalDateTime date) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM MunicipalManagerIsAssignedTo AS t1 JOIN Period AS p ON t1.period = p.id WHERE p.start <= =? AND COALESCE(p.end, =?) >= =?", new MunicipalManagerIsAssignedToRowMapper(), date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}
