package app.dao;

import app.dao.generic.PlaceDao;
import app.model.Area;
import app.model.Municipality;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaDao extends PlaceDao<Area> {
    public List<Area> getByMunicipality(Municipality municipality) {
        return executeQuery("WHERE municipality = ?", municipality.getId());
    }
}
