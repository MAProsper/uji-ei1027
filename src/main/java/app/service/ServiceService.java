package app.service;

import app.dao.*;
import app.model.Area;
import app.model.EnviromentalManager;
import app.model.MunicipalManager;
import app.model.ServiceType;
import app.service.generic.ScheduableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceService extends ScheduableService<app.model.Service> {
    @Autowired EnviromentalManagerDao enviromentalManagerDao;
    @Autowired MunicipalityDao municipalityDao;
    @Autowired ServiceTypeDao serviceTypeDao;
    @Autowired ServiceDao serviceDao;
    @Autowired AreaDao areaDao;

    @Override
    public List<app.model.Service> listObjects(HttpSession session, Integer arg) {
        List<app.model.Service> services = serviceDao.getByArea(arg);
        if (getUser(session) instanceof MunicipalManager) return services;
        return services.stream().filter(app.model.Service::isActive).collect(Collectors.toList());
    }

    @Override
    public app.model.Service addObject(HttpSession session, Integer arg) {
        app.model.Service service = super.addObject(session, arg);
        Optional<ServiceType> serviceType = serviceTypeDao.getAll().stream().filter(ServiceType::isActive).findAny();
        serviceType.ifPresent(type -> service.setServiceType(type.getId()));
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
        String manager = enviromentalManagerDao.getAll().stream().map(EnviromentalManager::getMail).collect(Collectors.joining(","));
        String mail = String.format("mailto:%s?subject=%s", manager, "Solicitud%20de%20nuevo%20tipo%20de%20servicio");
        return Map.of("municipality", municipalityDao.getParentOf(area), "area", area, "serviceType", serviceType, "typeSelect", types, "today", today, "mail", mail);
    }

    @Override
    public String redirectSelf(HttpSession session, Integer arg) {
        return String.format("../list/%d", serviceDao.getById(arg).getArea());
    }
}
