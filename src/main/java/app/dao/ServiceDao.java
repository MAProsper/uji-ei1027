package app.dao;

import app.dao.generic.Dao;
import app.model.Area;
import app.model.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceDao extends Dao<Service> {
    public ServiceDao() {
        super(Service.class);
    }

    public List<Service> getByArea(Area area) {
        try {
            return jdbc.query("SELECT * FROM Service WHERE area =?", mapper, area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
