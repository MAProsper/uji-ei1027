package app.dao;

import app.dao.generic.Dao;
import app.model.ServiceType;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceTypeDao extends Dao<ServiceType> {
    public ServiceTypeDao() {
        super(ServiceType.class);
    }
}
