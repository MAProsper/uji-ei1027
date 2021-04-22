package app.dao.area.areaImage;

import app.dao.Dao;
import app.model.area.Area;
import app.model.area.AreaImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AreaImageDao extends Dao<AreaImage> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(AreaImage areaImage) {
        jdbcTemplate.update("INSERT INTO AreaImage VALUES(?, ?, ?)",
                areaImage.getId(), areaImage.getArea(), areaImage.getImage());
    }

    public void update(AreaImage areaImage) {
        jdbcTemplate.update("UPDATE AreaImage SET id =?, area =?, image =?",
                areaImage.getId(), areaImage.getArea(), areaImage.getImage());
    }

    public List<AreaImage> getByArea(Area area) {
        try {
            return jdbcTemplate.query("SELECT * FROM AreaImage WHERE area =?", new AreaImageRowMapper(), area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
