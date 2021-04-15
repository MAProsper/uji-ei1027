package app.dao.citizen;

import app.dao.Dao;
import app.model.citizen.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class CitizenDao implements Dao<Citizen> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Citizen citizen) {
        jdbcTemplate.update("INSERT INTO Citizen VALUES(?)", citizen.getPerson());
    }

    public void delete(Citizen citizen) {
        jdbcTemplate.update("DELETE FROM Citizen  WHERE person = ?", citizen.getPerson());
    }

    public void update(Citizen citizen) {
        jdbcTemplate.update("UPDATE Citizen SET person =?", citizen.getPerson());
    }

    public List<Citizen> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM Citizen", new CitizenRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Citizen getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Citizen WHERE person =?", new CitizenRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}
