package dao;

import model.ControlStaffIsAssignetTo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlStaffIsAssignetToRowMapper implements RowMapper<ControlStaffIsAssignetTo> {
    @Override
    public ControlStaffIsAssignetTo mapRow(ResultSet resultSet, int i) throws SQLException {
        ControlStaffIsAssignetTo controlStaffIsAssignetTo = new ControlStaffIsAssignetTo();
        controlStaffIsAssignetTo.setId(resultSet.getInt("id"));
        controlStaffIsAssignetTo.setControlStaff(resultSet.getInt("control_staff"));
        controlStaffIsAssignetTo.setAreaPeriod(resultSet.getInt("area_period"));
        return controlStaffIsAssignetTo;
    }
}
