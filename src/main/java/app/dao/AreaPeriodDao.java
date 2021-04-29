package app.dao;

import app.ApplicationException;
import app.dao.generic.ScheduleableDao;
import app.model.AreaPeriod;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaPeriodDao extends ScheduleableDao<AreaPeriod> {
    @Override
    public void add(AreaPeriod object) {
        overlapValidator(object);
        super.add(object);
    }

    @Override
    public void update(AreaPeriod object) {
        overlapValidator(object);
        super.update(object);
    }

    public List<AreaPeriod> getByArea(int id) {
        return getByField("area", id);
    }

    protected void overlapValidator(AreaPeriod object) {
        List<AreaPeriod> periods = getByArea(object.getArea());
        if (periods.stream().anyMatch(period -> period.getId() != object.getId() && period.overlapsWith(object)))
            throw new ApplicationException("periodo se solapa con otro");
    }
}