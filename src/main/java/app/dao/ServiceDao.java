package app.dao;

import app.dao.generic.ScheduleableDao;
import app.model.Service;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceDao extends ScheduleableDao<Service> {
    public List<Service> getByArea(int id) {
        return getByField("area", id);
    }
}
