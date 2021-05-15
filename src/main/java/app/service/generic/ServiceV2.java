package app.service.generic;

import app.ApplicationException;
import app.dao.generic.Dao;
import app.model.generic.Model;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class ServiceV2<M extends Model> {
    @Autowired protected Dao<M> dao;

    public List<M> listObjects(HttpSession session) {
        return dao.getAll();
    }

    public List<M> listObjects(HttpSession session, int arg) {
        return dao.getAll();
    }

    public Map<String,String> listRequestData(HttpSession session) {
        return Collections.emptyMap();
    }

    public Map<String,String> listRequestData(HttpSession session, int arg) {
        return listRequestData(session);
    }

    public Map<String,String> listObjectData(M object) {
        return Collections.emptyMap();
    }

    public M addObject(HttpSession session) {
        return dao.getReflect().newInstance();
    }

    public M addObject(HttpSession session, int arg) {
        return addObject(session);
    }

    public Map<String,String> addRequestData(HttpSession session) {
        return listRequestData(session);
    }

    public Map<String,String> addRequestData(HttpSession session, int arg) {
        return addRequestData(session);
    }

    public void addProcess(M object, HttpSession session) {
        dao.add(object);
    }

    public void addProcess(M object, HttpSession session, int arg) {
        addProcess(object, session);
    }

    public String addRedirect(HttpSession session) {
        return "list";
    }

    public String addRedirect(HttpSession session, int arg) {
        return String.format("../list/%d", arg);
    }

    public M updateObject(HttpSession session) {
        throw new ApplicationException("No existe la operacion que esta realizando");
    }

    public M updateObject(HttpSession session, int arg) {
        return dao.getById(arg);
    }

    public Map<String,String> updateRequestData(HttpSession session) {
        return addRequestData(session);
    }

    public Map<String,String> updateRequestData(HttpSession session, int arg) {
        return updateRequestData(session);
    }

    public void updateProcess(M object, HttpSession session) {
        dao.update(object);
    }

    public void updateProcess(M object, HttpSession session, int arg) {
        updateProcess(object, session);
    }

    public String updateRedirect(HttpSession session) {
        return addRedirect(session);
    }

    public String updateRedirect(HttpSession session, int arg) {
        return addRedirect(session, arg);
    }

    public void deleteProcess(HttpSession session) {
        throw new ApplicationException("No existe la operacion que esta realizando");
    }

    public void deleteProcess(HttpSession session, int arg) {
        dao.delete(arg);
    }

    public String deleteRedirect(HttpSession session) {
        return addRedirect(session);
    }

    public String deleteRedirect(HttpSession session, int arg) {
        return addRedirect(session, arg);
    }

    public String getName() {
        return dao.getMapper().getTableName();
    }
}
