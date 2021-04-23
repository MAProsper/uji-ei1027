package app.dao.controlStaff;

import app.dao.Dao;
import app.model.area.Area;
import app.model.controlStaff.ControlStaff;
import app.model.controlStaff.ControlStaffIsAssignedTo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ControlStaffIsAssignedToDao extends Dao<ControlStaffIsAssignedTo> {
    public ControlStaffIsAssignedToDao() {
        super(ControlStaffIsAssignedTo.class);
    }

    public List<ControlStaffIsAssignedTo> getByControlStaff(ControlStaff controlStaff) {
        try {
            return jdbc.query("SELECT * FROM ControlStaffIsAssignedTo WHERE control_staff =?", mapper, controlStaff.getPerson());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<ControlStaffIsAssignedTo> getByArea(Area area) {
        try {
            return jdbc.query("SELECT * FROM ControlStaffIsAssignedTo WHERE area =?", mapper, area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ControlStaffIsAssignedTo getByDate(LocalDateTime date) {
        try {
            return jdbc.queryForObject("SELECT * FROM ControlStaffIsAssignedTo AS t1 JOIN Period AS p ON t1.period = p.id WHERE p.start <= =? AND COALESCE(p.end, =?) >= =?", mapper, date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}