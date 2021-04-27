package app.dao;

import app.dao.generic.Dao;
import app.model.Area;
import app.model.AreaPeriod;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaPeriodDao extends Dao<AreaPeriod> {
    public AreaPeriodDao() {
        super(AreaPeriod.class);
    }

    public List<AreaPeriod> getByArea(Area area) {
        return executeQuery("WHERE area = ?", area.getId());
    }
}
