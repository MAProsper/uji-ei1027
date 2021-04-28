package app.dao;

import app.dao.generic.Dao;
import app.model.Area;
import app.model.Service;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceDao extends Dao<Service> {
    public List<Service> getByArea(Area area) {
        return executeQuery("WHERE area = ?", area.getId());
    }
}
