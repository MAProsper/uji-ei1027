package app.dao;

import app.dao.generic.PersonDao;
import app.model.MunicipalManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MunicipalManagerDao extends PersonDao<MunicipalManager> {
    public List<MunicipalManager> getByMunicipality(int id) {
        return getByField("municipality", id);
    }
}
