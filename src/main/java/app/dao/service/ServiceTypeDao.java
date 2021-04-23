package app.dao.service;

import app.dao.Dao;
import app.model.service.ServiceType;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceTypeDao extends Dao<ServiceType> {
    public ServiceTypeDao() {
        super(ServiceType.class);
    }

    public void update(ServiceType serviceType) {
        jdbc.update("UPDATE ServiceType SET id =?, type =?, description =?",
                serviceType.getId(), serviceType.getType(), serviceType.getDescription());
    }

}
