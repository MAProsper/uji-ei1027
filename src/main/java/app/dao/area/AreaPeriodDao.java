package app.dao.area;

import app.dao.Dao;
import app.model.area.Area;
import app.model.area.AreaPeriod;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaPeriodDao extends Dao<AreaPeriod> {
    public AreaPeriodDao() {
        super(AreaPeriod.class);
    }

    public AreaPeriod getById(int id) {
        try {
            return jdbc.queryForObject("SELECT * FROM AreaPeriod WHERE id =?", mapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<AreaPeriod> getByArea(Area area) {
        try {
            return jdbc.query("SELECT * FROM AreaPeriod WHERE area =?", mapper, area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public AreaPeriod getByDate(LocalDateTime date) {
        try {
            return jdbc.queryForObject("SELECT * FROM AreaPeriod AS t1 JOIN Period AS p ON t1.period = p.id WHERE p.start <= =? AND COALESCE(p.end, =?) >= =?", mapper, date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}
