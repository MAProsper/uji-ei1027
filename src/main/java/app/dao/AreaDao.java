package app.dao;

import app.dao.generic.PlaceSignableDao;
import app.model.Area;
import app.model.Municipality;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaDao extends PlaceSignableDao<Area> {
    public AreaDao() {
        super(Area.class);
    }

    public List<Area> getByMunicipality(Municipality municipality) {
        try {
            return jdbc.query("SELECT * FROM Area WHERE municipality =?", mapper, municipality.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
