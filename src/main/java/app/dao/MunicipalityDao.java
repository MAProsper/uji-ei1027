package app.dao;

import app.dao.generic.PlaceSignableDao;
import app.model.Municipality;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipalityDao extends PlaceSignableDao<Municipality> {
    public MunicipalityDao() {
        super(Municipality.class);
    }
}
