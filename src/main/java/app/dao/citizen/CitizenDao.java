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
public class CitizenDao extends Dao<Citizen> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Citizen citizen) {
        jdbcTemplate.update("INSERT INTO Citizen VALUES(?)", citizen.getPerson());
    }

    public void update(Citizen citizen) {
        jdbcTemplate.update("UPDATE Citizen SET person =?", citizen.getPerson());
    }

}
