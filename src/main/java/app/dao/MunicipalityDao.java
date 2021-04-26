package app.dao;

import app.dao.generic.Dao;
import app.model.Municipality;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipalityDao extends Dao<Municipality> {
    public MunicipalityDao() {
        super(Municipality.class);
    }
}
