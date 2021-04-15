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
public class PeriodDao implements Dao<Period> {
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

    public void delete(Period period) {
        jdbcTemplate.update("DELETE FROM Period WHERE id =?",
                period.getId());
    }

    public List<Period> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM Period", new PeriodRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Period getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Period WHERE id =?", new PeriodRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}

