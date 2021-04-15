package app.dao.area.areaPeriod;

import app.dao.Dao;
import app.model.area.Area;
import app.model.area.AreaPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AreaPeriodDao implements Dao<AreaPeriod> {
    private JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(AreaPeriod areaPeriod) {
        jdbcTemplate.update("INSERT INTO AreaPeriod VALUES(?, ?, ?)",
                areaPeriod.getId(), areaPeriod.getArea(), areaPeriod.getPeriod());
    }

    public void update(AreaPeriod areaPeriod) {
        jdbcTemplate.update("UPDATE AreaPeriod SET id =?, area =?, period =?",
                areaPeriod.getId(), areaPeriod.getArea(), areaPeriod.getPeriod());
    }

    public void delete(AreaPeriod areaPeriod) {
        jdbcTemplate.update("DELETE FROM AreaPeriod WHERE id =?",
                areaPeriod.getId());
    }

    public List<AreaPeriod> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM AreaPeriod", new AreaPeriodRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public AreaPeriod getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM AreaPeriod WHERE id =?", new AreaPeriodRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<AreaPeriod> getByArea(Area area) {
        try {
            return jdbcTemplate.query("SELECT * FROM AreaPeriod WHERE area =?", new AreaPeriodRowMapper(), area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}
