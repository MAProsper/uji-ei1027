package app.service.generic;

import app.ApplicationException;
import app.dao.generic.Dao;
import app.model.generic.Model;
import app.model.generic.Person;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class Service<M extends Model> {
    @Autowired protected Dao<M> dao;

    public List<M> listObjects(HttpSession session, Integer arg) {
        return dao.getAll();
    }

    public Map<String, Object> listRequestData(HttpSession session, Integer arg) {
        return Collections.emptyMap();
    }

    public Map<String, Object> listObjectData(M object) {
        return Collections.emptyMap();
    }

    public M addObject(HttpSession session, Integer arg) {
        return dao.getReflect().newInstance();
    }

    public Map<String, Object> processData(HttpSession session, Integer arg) {
        return listRequestData(session, arg);
    }

    public void addProcess(M object, HttpSession session, Integer arg) {
        dao.add(object);
    }

    public M updateObject(HttpSession session, Integer arg) {
        if (arg == null)
            throw new ApplicationException("No existe operacion por defecto");
        return dao.getById(arg);
    }

    public void updateProcess(M object, HttpSession session, Integer arg) {
        dao.update(object);
    }

    public void deleteProcess(HttpSession session, Integer arg) {
        if (arg == null)
            throw new ApplicationException("No existe operacion por defecto");
        dao.delete(arg);
    }

    public void requestProcess(HttpSession session, Integer arg, M object) {}

    public String getRedirect(HttpSession session, Integer arg) {
        return arg == null ? "list" : String.format("../list/%d", arg);
    }

    public String getName() {
        return dao.getMapper().getTableName();
    }

    protected Person getUser(HttpSession session) {
        return (Person) session.getAttribute("user");
    }
}
