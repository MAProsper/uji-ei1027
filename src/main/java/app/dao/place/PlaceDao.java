package app.dao.place;

import app.dao.Dao;
import app.model.place.Place;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDao extends Dao<Place> {
    public PlaceDao() {
        super(Place.class);
    }
}
