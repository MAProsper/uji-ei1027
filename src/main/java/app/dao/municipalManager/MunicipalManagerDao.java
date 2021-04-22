package app.dao.municipalManager;

import app.dao.Dao;
import app.model.municipalManager.MunicipalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class MunicipalManagerDao extends Dao<MunicipalManager> {
    @Autowired JdbcTemplate jdbcTemplate;

    public void add(MunicipalManager municipalManager) {
        jdbcTemplate.update("INSERT INTO MunicipalManager VALUES(?)", municipalManager.getPerson());
    }

    public void update(MunicipalManager municipalManager) {
        jdbcTemplate.update("UPDATE MunicipalManager SET person =?", municipalManager.getPerson());
    }

    public List<MunicipalManager> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM MunicipalManager", new MunicipalManagerRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
