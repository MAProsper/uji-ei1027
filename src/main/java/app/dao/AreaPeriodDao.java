package app.dao;

import app.ApplicationException;
import app.dao.generic.ScheduleableDao;
import app.model.AreaPeriod;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaPeriodDao extends ScheduleableDao<AreaPeriod> {
    public List<AreaPeriod> getByArea(int id) {
        return getByField("area", id);
    }
}