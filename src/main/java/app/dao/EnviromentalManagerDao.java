package app.dao;

import app.dao.generic.PersonDao;
import app.model.EnviromentalManager;
import app.model.MunicipalManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnviromentalManagerDao extends PersonDao<EnviromentalManager> {
}
