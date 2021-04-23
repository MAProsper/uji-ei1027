package app.dao.area;

import app.dao.Dao;
import app.model.area.Area;
import app.model.area.AreaImage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaImageDao extends Dao<AreaImage> {
    public AreaImageDao() {
        super(AreaImage.class);
    }

    public List<AreaImage> getByArea(Area area) {
        try {
            return jdbc.query("SELECT * FROM AreaImage WHERE area =?", mapper, area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
