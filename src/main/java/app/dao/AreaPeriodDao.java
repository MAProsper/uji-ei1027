package app.dao;

import app.ApplicationException;
import app.dao.generic.Dao;
import app.model.AreaPeriod;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaPeriodDao extends Dao<AreaPeriod> {
    @Override
    public void add(AreaPeriod object) {
        validator(object);
        super.add(object);
    }

    @Override
    public void update(AreaPeriod object) {
        validator(object);
        super.update(object);
    }

    protected void validator(AreaPeriod object) {
        List<AreaPeriod> periods = getByField("area", object.getArea());
        if (periods.stream().anyMatch(period -> period.getId() != object.getId() && overlap(period, object)))
            throw new ApplicationException("periodo se solapa con otro");
    }

    protected boolean overlap(AreaPeriod a, AreaPeriod b) {
        return a.getPeriodStart().compareTo(b.getPeriodEnd()) < 0 && a.getPeriodEnd().compareTo(b.getPeriodStart()) < 0;
    }
}
