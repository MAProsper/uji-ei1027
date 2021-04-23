package app.dao.area;

import app.dao.Dao;
import app.model.area.Area;
import app.model.area.AreaComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AreaCommentDao extends Dao<AreaComment> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(AreaComment areaComment) {
        jdbcTemplate.update("INSERT INTO AreaComment VALUES(?, ?, ?)",
                areaComment.getId(), areaComment.getArea(), areaComment.getComment());
    }

    public void update(AreaComment areaComment) {
        jdbcTemplate.update("UPDATE AreaComment SET id =?, area =?, comment =?",
                areaComment.getId(), areaComment.getArea(), areaComment.getComment());
    }

    public List<AreaComment> getByArea(Area area) {
        try {
            return jdbcTemplate.query("SELECT * FROM AreaComment WHERE area =?", new AreaCommentRowMapper(), area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
