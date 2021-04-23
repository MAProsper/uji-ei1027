package app.dao.area;

import app.dao.Dao;
import app.model.area.Area;
import app.model.area.AreaComment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaCommentDao extends Dao<AreaComment> {
    public AreaCommentDao() {
        super(AreaComment.class);
    }

    public List<AreaComment> getByArea(Area area) {
        try {
            return jdbc.query("SELECT * FROM AreaComment WHERE area =?", mapper, area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
