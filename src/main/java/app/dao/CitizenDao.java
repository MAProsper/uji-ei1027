package app.dao;

import app.dao.generic.PersonDao;
import app.model.Citizen;
import org.springframework.stereotype.Repository;

@Repository
public class CitizenDao extends PersonDao<Citizen> {
}
