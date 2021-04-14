package dao.controlStaff;

import model.controlStaff.ControlStaff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlStaffRowMapper implements RowMapper<ControlStaff> {
    @Override
    public ControlStaff mapRow(ResultSet resultSet, int i) throws SQLException {
        ControlStaff controlStaff = new ControlStaff();
        controlStaff.setPerson(resultSet.getInt("person"));
        return controlStaff;
    }
}