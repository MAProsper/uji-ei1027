package app.dao.generic;

import app.dao.generic.SignableDao;
import app.model.Place;
import org.springframework.stereotype.Repository;

@Repository
public abstract class PlaceSignableDao<T extends Place> extends SignableDao<T> {
    public PlaceSignableDao(Class<T> cls) {
        super(cls);
    }
}
