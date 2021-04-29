package app.dao;

import app.dao.generic.Dao;
import app.dao.generic.ScheduleableDao;
import app.model.Service;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceDao extends ScheduleableDao<Service> {
}
