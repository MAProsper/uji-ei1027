package app.service;

import app.dao.ServiceDao;
import app.service.generic.ScheduableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ServiceService extends ScheduableService<app.model.Service> {
    @Autowired ServiceDao serviceDao;

    @Override
    public List<app.model.Service> listObjects(HttpSession session, Integer arg) {
        return serviceDao.getByArea(arg);
    }

    @Override
    public app.model.Service addObject(HttpSession session, Integer arg) {
        app.model.Service service = super.addObject(session, arg);
        service.setArea(arg);
        return service;
    }
}
