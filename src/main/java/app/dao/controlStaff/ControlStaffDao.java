package app.dao.controlStaff;

import app.dao.Dao;
import app.model.controlStaff.ControlStaff;
import org.springframework.stereotype.Repository;

@Repository
public class ControlStaffDao extends Dao<ControlStaff> {
    public ControlStaffDao() {
        super(ControlStaff.class);
    }
}
