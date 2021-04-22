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
public class ControlStaffDao extends Dao<ControlStaff> {
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
}
