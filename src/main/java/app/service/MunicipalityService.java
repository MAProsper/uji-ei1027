package app.service;

import app.dao.MunicipalityDao;
import app.model.EnviromentalManager;
import app.model.MunicipalManager;
import app.model.Municipality;
import app.model.generic.Person;
import app.service.generic.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipalityService extends PlaceService<Municipality> {

    @Autowired MunicipalityDao municipalityDao;

    @Override
    public List<Municipality> listObjects(HttpSession session, Integer arg) {
        Person user = getUser(session);
        if (user instanceof MunicipalManager)
            return Collections.singletonList(dao.getById(((MunicipalManager) user).getMunicipality()));
        List<Municipality> municipality = super.listObjects(session, arg);
        if (user instanceof EnviromentalManager) return municipality;
        return municipality.stream().filter(Municipality::isActive).collect(Collectors.toList());
    }

    @Override
    public Municipality updateObject(HttpSession session, Integer arg) {
        return this.municipalityDao.getById( ((MunicipalManager)getUser(session)).getMunicipality() );
    }
}
