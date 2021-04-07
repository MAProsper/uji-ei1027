package model.area.areaComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class AreaCommentDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(AreaComment areaComment) {
        jdbcTemplate.update("INSERT INTO AreaComment VALUES(?, ?, ?)",
                areaComment.getId(), areaComment.getArea(), areaComment.getComment());
    }

    public void update(AreaComment areaComment) {
        jdbcTemplate.update("UPDATE AreaComment SET id =?, area =?, comment =?",
                areaComment.getId(), areaComment.getArea(), areaComment.getComment());
    }

    public void delete(AreaComment areaComment) {
        jdbcTemplate.update("DELETE FROM AreaComment WHERE id =?",
                areaComment.getId());
    }

    public List<AreaComment> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM AreaComment", new AreaCommentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public AreaComment get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM AreaComment WHERE id =?", new AreaCommentRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
