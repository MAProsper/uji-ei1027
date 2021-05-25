package app.dao;

import app.dao.generic.PersonDao;
import app.model.ControlStaff;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ControlStaffDao extends PersonDao<ControlStaff> {
    public List<ControlStaff> getByAreaPeriod(int id) {
        return getByField("areaPeriod", id);
    }


}
