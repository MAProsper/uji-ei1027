package app.dao;

import app.dao.generic.PlaceDao;
import app.model.Area;
import app.model.Zone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaDao extends PlaceDao<Area> {
    public List<Area> getByMunicipality(int id) {
        return getByField("municipality", id);
    }
}
