package app.dao.generic;

import app.model.generic.Place;
import org.springframework.stereotype.Repository;

@Repository
public abstract class PlaceDao<T extends Place> extends SignableDao<T> {
}
