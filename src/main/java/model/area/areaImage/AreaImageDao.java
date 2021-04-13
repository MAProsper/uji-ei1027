package model.area.areaImage;

import model.area.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class AreaImageDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(AreaImage areaImage) {
        jdbcTemplate.update("INSERT INTO AreaImage VALUES(?, ?, ?)",
                areaImage.getId(), areaImage.getArea(), areaImage.getImage());
    }

    public void update(AreaImage areaImage) {
        jdbcTemplate.update("UPDATE AreaImage SET id =?, area =?, image =?",
                areaImage.getId(), areaImage.getArea(), areaImage.getImage());
    }

    public void delete(AreaImage areaImage) {
        jdbcTemplate.update("DELETE FROM AreaImage WHERE id =?",
                areaImage.getId());
    }

    public List<AreaImage> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM AreaImage", new AreaImageRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public AreaImage get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM AreaImage WHERE id =?", new AreaImageRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<AreaImage> get(Area area) {
        try {
            return jdbcTemplate.query("SELECT * FROM AreaImage WHERE area =?", new AreaImageRowMapper(), area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
