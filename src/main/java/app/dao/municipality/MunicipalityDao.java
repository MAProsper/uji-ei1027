package app.dao.municipality;

import app.dao.Dao;
import app.model.municipality.Municipality;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipalityDao extends Dao<Municipality> {
    public MunicipalityDao() {
        super(Municipality.class);
    }
}
