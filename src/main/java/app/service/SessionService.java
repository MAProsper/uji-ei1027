package app.service;

import app.dao.CitizenDao;
import app.dao.ControlStaffDao;
import app.dao.EnviromentalManagerDao;
import app.dao.MunicipalManagerDao;
import app.dao.generic.PersonDao;
import app.model.Session;
import app.model.generic.Person;
import app.service.generic.Service;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Service
public class SessionService extends Service<Session> {
    @Autowired protected PasswordEncryptor encryptor;
    @Autowired protected EnviromentalManagerDao enviromentalManagerDao;
    @Autowired protected MunicipalManagerDao municipalManagerDao;
    @Autowired protected ControlStaffDao controlStaffDao;
    @Autowired protected CitizenDao citizenDao;

    public Person getUser(Session session) {
        Person person = getByIdentification(session.getIdentification());
        return person != null && encryptor.checkPassword(session.getPassword(), person.getPassword()) ? person : null;
    }

    protected Person getByIdentification(String identification) {
        return getPriority().stream().map(dao -> dao.getByIdentification(identification)).filter(Objects::nonNull).findAny().orElse(null);
    }

    protected List<PersonDao<?>> getPriority() {
        return List.of(enviromentalManagerDao, municipalManagerDao, controlStaffDao, citizenDao);
    }

    @Override
    public String addProcess(Session object, HttpSession session) {
        session.setAttribute("user", getUser(object));
        return getRedirect(session);
    }

    @Override
    public String deleteProcess(HttpSession session) {
        session.removeAttribute("user");
        return getRedirect(session);
    }

    protected String getRedirect(HttpSession session) {
        String referrer = (String) session.getAttribute("referrer");
        return referrer != null ? referrer : "/";
    }
}
