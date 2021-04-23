package app.dao.controlStaff;

import app.dao.Dao;
import app.model.area.Area;
import app.model.controlStaff.ControlStaff;
import app.model.controlStaff.ControlStaffIsAssignedTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ControlStaffIsAssignedToDao extends Dao<ControlStaffIsAssignedTo> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(ControlStaffIsAssignedTo controlStaffIsAssignedTo) {
        jdbcTemplate.update("INSERT INTO ControlStaffIsAssignedTo VALUES(?, ?, ?)",
                controlStaffIsAssignedTo.getId(), controlStaffIsAssignedTo.getControlStaff(), controlStaffIsAssignedTo.getAreaPeriod());
    }

    public void update(ControlStaffIsAssignedTo controlStaffIsAssignedTo) {
        jdbcTemplate.update("UPDATE ControlStaffIsAssignedTo SET id =?, control_staff =?, area_period =?",
                controlStaffIsAssignedTo.getId(), controlStaffIsAssignedTo.getControlStaff(), controlStaffIsAssignedTo.getAreaPeriod());
    }

    public List<ControlStaffIsAssignedTo> getByControlStaff(ControlStaff controlStaff) {
        try {
            return jdbcTemplate.query("SELECT * FROM ControlStaffIsAssignedTo WHERE control_staff =?", new ControlStaffIsAssignedToRowMapper(), controlStaff.getPerson());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<ControlStaffIsAssignedTo> getByArea(Area area) {
        try {
            return jdbcTemplate.query("SELECT * FROM ControlStaffIsAssignedTo WHERE area =?", new ControlStaffIsAssignedToRowMapper(), area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ControlStaffIsAssignedTo getByDate(LocalDateTime date) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ControlStaffIsAssignedTo AS t1 JOIN Period AS p ON t1.period = p.id WHERE p.start <= =? AND COALESCE(p.end, =?) >= =?", new ControlStaffIsAssignedToRowMapper(), date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}