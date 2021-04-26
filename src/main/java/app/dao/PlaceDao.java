package app.dao;

import app.dao.generic.Dao;
import app.model.Place;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDao extends Dao<Place> {
    public PlaceDao() {
        super(Place.class);
    }
}
