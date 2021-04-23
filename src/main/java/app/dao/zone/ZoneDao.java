package app.dao.zone;

import app.dao.Dao;
import app.model.area.Area;
import app.model.zone.Zone;
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
            return jdbc.query("SELECT * FROM Zone WHERE area =?", mapper, area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
