package model.area.areaPeriod;

import model.area.areaImage.AreaImage;
import model.area.areaImage.AreaImageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class AreaPeriodDao {
    private JdbcTemplate jdbcTemplate;

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

    public List<AreaPeriod> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM AreaPeriod", new AreaPeriodRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public AreaPeriod get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM AreaPeriod WHERE id =?", new AreaPeriodRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
