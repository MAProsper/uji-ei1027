package app.service;

import app.dao.CitizenDao;
import app.dao.ControlStaffDao;
import app.dao.EnviromentalManagerDao;
import app.dao.MunicipalManagerDao;
import app.dao.generic.PersonDao;
import app.model.EnviromentalManager;
import app.model.Session;
import app.model.generic.Person;
import app.service.generic.ServiceV2;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SessionService extends ServiceV2<Session> {
    @Autowired protected PasswordEncryptor encryptor;
    @Autowired protected EnviromentalManagerDao enviromentalManagerDao;
    @Autowired protected MunicipalManagerDao municipalManagerDao;
    @Autowired protected ControlStaffDao controlStaffDao;
    @Autowired protected CitizenDao citizenDao;

    public Person getUser(Session session) {
        Person person = getByIdentification(session.getIdentification());
        return person!= null && encryptor.checkPassword(session.getPassword(), person.getPassword()) ? person : null;
    }

    protected Person getByIdentification(String identification) {
        return getPriority().stream().map(dao -> dao.getByIdentification(identification)).filter(Objects::nonNull).findAny().orElse(null);
    }

    protected List<PersonDao<?>> getPriority() {
        return List.of(enviromentalManagerDao, municipalManagerDao, controlStaffDao, citizenDao);
    }

    @Override
    public void addProcess(Session object, HttpSession session) {
        session.setAttribute("user", getUser(object));
    }

    @Override
    public String addRedirect(HttpSession session) {
        String referrer = (String) session.getAttribute("referrer");
        return referrer != null ? referrer : deleteRedirect(session);
    }

    @Override
    public void deleteProcess(HttpSession session) {
        session.invalidate();
    }

    @Override
    public String deleteRedirect(HttpSession session) {
        return "/";
    }
}
