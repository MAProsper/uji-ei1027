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

    public Map<String, Object> data(M object) {
        return Collections.emptyMap();
    }

    /**
     * Obtener objetos a mostrar en el listado
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @return objetos a mostrar
     */
    public List<M> listObjects(HttpSession session, Integer arg) {
        return dao.getAll();
    }

    /**
     * Obtener objeto a presentar en el registro (incluyendo valores por defecto)
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @return objeto para el formulario
     */
    public M addObject(HttpSession session, Integer arg) {
        return dao.getReflect().newInstance();
    }

    /**
     * Obtener objeto a presentar en actualizacion (incluyendo valores por defecto)
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @return objeto para el formulario
     */
    public M updateObject(HttpSession session, Integer arg) {
        if (arg == null)
            throw new ApplicationException("No existe operacion por defecto");
        return dao.getById(arg);
    }

    /**
     * Procesar el nuevo objeto a añadir (validado)
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @param object  objeto referencia
     */
    public void addProcess(HttpSession session, Integer arg, M object) {
        dao.add(object);
    }

    /**
     * Procesar el objeto a actualizar (validado)
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @param object  objeto referencia
     */
    public void updateProcess(HttpSession session, Integer arg, M object) {
        dao.update(object);
    }

    /**
     * Peticion del eliminado
     *
     * @param session sesion
     * @param arg     argumento opcional
     */
    public void deleteProcess(HttpSession session, Integer arg) {
        if (arg == null)
            throw new ApplicationException("No existe operacion por defecto");
        dao.delete(arg);
    }

    /**
     * Obtener URL a redirecion despues de modificaciones
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @return URL a redirecionar
     */
    public String getRedirect(HttpSession session, Integer arg) {
        return arg == null ? "list" : String.format("../list/%d", arg);
    }

    /**
     * Obtener nombre del modelo servirvido
     *
     * @return nombre del modelo
     */
    public String getName() {
        return dao.getMapper().getTableName();
    }

    /**
     * Obtener el usuario de la sesion
     *
     * @param session sesion
     * @return usuario
     */
    protected Person getUser(HttpSession session) {
        return (Person) session.getAttribute("user");
    }

    /**
     * Cierra la sesion actual
     *
     * @param session sesion
     */
    protected void deleteSession(HttpSession session) {
        session.removeAttribute("user");
    }
}
