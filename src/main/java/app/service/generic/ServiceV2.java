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

    public void deleteObject(HttpSession session) {
        throw new ApplicationException("No existe la operacion que esta realizando");
    }

    public void deleteObject(HttpSession session, int arg) {
        dao.delete(arg);
    }

    public Dao<M> getDao() {
        return dao;
    }

    public String getName() {
        return dao.getMapper().getTableName();
    }
}
