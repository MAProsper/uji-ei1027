package app.service;

import app.model.EnviromentalManager;
import app.model.Municipality;
import app.service.generic.PlaceService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipalityService extends PlaceService<Municipality> {
    @Override
    public List<Municipality> listObjects(HttpSession session, Integer arg) {
        List<Municipality> municipality = super.listObjects(session, arg);
        if (getUser(session) instanceof EnviromentalManager) return municipality;
        return municipality.stream().filter(Municipality::isActive).collect(Collectors.toList());
    }
}
