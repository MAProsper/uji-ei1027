package app.service;

import app.dao.MunicipalManagerDao;
import app.dao.MunicipalityDao;
import app.model.MunicipalManager;
import app.service.generic.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MunicipalManagerService extends PersonService<MunicipalManager> {
    @Autowired MunicipalityDao municipalityDao;

    @Override
    public List<MunicipalManager> listObjects(HttpSession session, Integer arg) {
        return Collections.singletonList((MunicipalManager) getUser(session));
    }

    @Override
    public Map<String, Object> data(MunicipalManager object) {
        Map<String, Object> data = super.data(object);
        data.put("municipality", municipalityDao.getParentOf(object));
        return data;
    }

    @Override
    public MunicipalManager addObject(HttpSession session, Integer arg) {
        return (MunicipalManager) getUser(session);
    }

    @Override
    public MunicipalManager updateObject(HttpSession session, Integer arg) {
        return addObject(session, arg);
    }

    @Override
    public void updateProcess(HttpSession session, Integer arg, MunicipalManager object) {
        super.updateProcess(session, arg, object);
        addSession(session, object);
    }
}
