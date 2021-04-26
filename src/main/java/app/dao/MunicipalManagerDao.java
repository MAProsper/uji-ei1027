package app.dao;

import app.dao.generic.Dao;
import app.model.MunicipalManager;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipalManagerDao extends Dao<MunicipalManager> {
    public MunicipalManagerDao() {
        super(MunicipalManager.class);
    }
}
