package model.controlStaff.controlStaffIsAssignedTo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlStaffIsAssignedToRowMapper implements RowMapper<ControlStaffIsAssignedTo> {
    @Override
    public ControlStaffIsAssignedTo mapRow(ResultSet resultSet, int i) throws SQLException {
        ControlStaffIsAssignedTo controlStaffIsAssignedTo = new ControlStaffIsAssignedTo();
        controlStaffIsAssignedTo.setId(resultSet.getInt("id"));
        controlStaffIsAssignedTo.setControlStaff(resultSet.getInt("control_staff"));
        controlStaffIsAssignedTo.setAreaPeriod(resultSet.getInt("area_period"));
        return controlStaffIsAssignedTo;
    }
}
