package app.dao.period;

import app.dao.Dao;
import app.model.period.Period;
import org.springframework.stereotype.Repository;

@Repository
public class PeriodDao extends Dao<Period> {
    public PeriodDao() {
        super(Period.class);
    }
}

