package app.service;

import app.dao.AreaDao;
import app.dao.MunicipalityDao;
import app.model.Area;
import app.model.Municipality;
import app.service.generic.ServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class AreaService extends ServiceV2<Area> {
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaDao areaDao;

    @Override
    public List<Area> listObjects(HttpSession session, int arg) {
        return areaDao.getByMunicipality(arg);
    }

    @Override
    public Map<String, String> listRequestData(HttpSession session, int arg) {
        return Map.of("municipality", municipalityDao.getById(arg).getName());
    }
}
