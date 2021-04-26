package app.dao;

import app.dao.generic.Dao;
import app.model.Area;
import app.model.Zone;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ZoneDao extends Dao<Zone> {
    public ZoneDao() {
        super(Zone.class);
    }

    public List<Zone> getByArea(Area area) {
        try {
            return jdbc.query("SELECT * FROM Zone WHERE area =?", mapper, area.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
