package app.dao;

import app.dao.generic.Dao;
import app.dao.generic.PersonDao;
import app.model.MunicipalManager;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipalManagerDao extends PersonDao<MunicipalManager> {
}
