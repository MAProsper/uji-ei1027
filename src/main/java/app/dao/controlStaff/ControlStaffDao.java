package app.dao.controlStaff;

import app.dao.Dao;
import app.model.controlStaff.ControlStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ControlStaffDao implements Dao<ControlStaff> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(ControlStaff controlStaff) {
        jdbcTemplate.update("INSERT INTO ControlStaff VALUES(?)",
                controlStaff.getPerson());
    }

    public void update(ControlStaff controlStaff) {
        jdbcTemplate.update("UPDATE ControlStaff SET person =?",
                controlStaff.getPerson());
    }

    public void delete(ControlStaff controlStaff) {
        jdbcTemplate.update("DELETE FROM ControlStaff WHERE person =?",
                controlStaff.getPerson());
    }

    public List<ControlStaff> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM ControlStaff", new ControlStaffRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ControlStaff getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ControlStaff WHERE person =?", new ControlStaffRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}
