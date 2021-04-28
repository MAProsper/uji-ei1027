package app.dao;

import app.dao.generic.PlaceDao;
import app.model.Area;
import app.model.Zone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZoneDao extends PlaceDao<Zone> {
    public List<Zone> getByArea(Area area) {
        return executeQuery("WHERE area = ?", area.getId());
    }
}
