package app.dao.municipalManager;

import app.dao.Dao;
import app.model.municipalManager.MunicipalManager;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipalManagerDao extends Dao<MunicipalManager> {
    public MunicipalManagerDao() {
        super(MunicipalManager.class);
    }
}
