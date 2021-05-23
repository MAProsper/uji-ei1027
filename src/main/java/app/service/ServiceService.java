package app.service;

import app.dao.AreaDao;
import app.dao.MunicipalityDao;
import app.dao.ServiceDao;
import app.dao.ServiceTypeDao;
import app.model.Area;
import app.model.ServiceType;
import app.model.Zone;
import app.service.generic.ScheduableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceService extends ScheduableService<app.model.Service> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired ServiceTypeDao serviceTypeDao;
    @Autowired ServiceDao serviceDao;
    @Autowired AreaDao areaDao;

    @Override
    public List<app.model.Service> listObjects(HttpSession session, Integer arg) {
        return serviceDao.getByArea(arg);
    }

    @Override
    public app.model.Service addObject(HttpSession session, Integer arg) {
        app.model.Service service = super.addObject(session, arg);
        Optional<ServiceType> serviceType = serviceTypeDao.getAll().stream().filter(ServiceType::isActive).findAny();
        serviceType.ifPresent(type -> service.setServiceType(type.getId()));
        service.setScheduleStart(LocalDate.now());
        service.setArea(arg);
        return service;
    }

    @Override
    public Map<String, Object> data(app.model.Service service) {
        LocalDate today = LocalDate.now();
        Area area = areaDao.getParentOf(service);
        List<ServiceType> types = serviceTypeDao.getAll();
        ServiceType serviceType = serviceTypeDao.getParentOf(service);
        if (serviceType == null) serviceType = new ServiceType();
        return Map.of("municipality", municipalityDao.getParentOf(area), "area", area, "serviceType", serviceType, "typeSelect", types, "today", today);
    }
}
