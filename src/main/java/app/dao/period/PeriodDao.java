package app.dao.period;

import app.dao.Dao;
import app.model.period.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PeriodDao extends Dao<Period> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Period period) {
        jdbcTemplate.update("INSERT INTO Period VALUES(?, ?, ?)",
                period.getId(), period.getStart(), period.getFinish());
    }

    public void update(Period period) {
        jdbcTemplate.update("UPDATE Period SET id =?, start =?, finish =?",
                period.getId(), period.getStart(), period.getFinish());
    }
}

