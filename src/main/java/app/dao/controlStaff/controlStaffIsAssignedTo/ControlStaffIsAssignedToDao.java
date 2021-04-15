package app.dao.controlStaff.controlStaffIsAssignedTo;

import app.dao.Dao;
import app.model.area.Area;
import app.model.controlStaff.ControlStaff;
import app.model.controlStaff.ControlStaffIsAssignedTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ControlStaffIsAssignedToDao implements Dao<ControlStaffIsAssignedTo> {
    private JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(ControlStaffIsAssignedTo controlStaffIsAssignedTo) {
        jdbcTemplate.update("INSERT INTO ControlStaffIsAssignedTo VALUES(?, ?, ?)",
                controlStaffIsAssignedTo.getId(), controlStaffIsAssignedTo.getControlStaff(), controlStaffIsAssignedTo.getAreaPeriod());
    }

    public void update(ControlStaffIsAssignedTo controlStaffIsAssignedTo) {
        jdbcTemplate.update("UPDATE ControlStaffIsAssignedTo SET id =?, control_staff =?, area_period =?",
                controlStaffIsAssignedTo.getId(), controlStaffIsAssignedTo.getControlStaff(), controlStaffIsAssignedTo.getAreaPeriod());
    }

    public void delete(ControlStaffIsAssignedTo controlStaffIsAssignedTo) {
        jdbcTemplate.update("DELETE FROM ControlStaffIsAssignedTo WHERE id =?",
                controlStaffIsAssignedTo.getId());
    }

    public List<ControlStaffIsAssignedTo> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM ControlStaffIsAssignedTo", new ControlStaffIsAssignedToRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ControlStaffIsAssignedTo getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ControlStaffIsAssignedTo WHERE id =?", new ControlStaffIsAssignedToRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}