package app.service.generic;

import app.dao.generic.Dao;
import app.model.generic.Model;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public abstract class Service<M extends Model> {
    @Autowired protected Dao<M> dao;

    public Dao<M> getDao() {
        return dao;
    }

    /**
     * Obtiene los objectos relavantes para esta sesion
     *
     * @param session session referencia
     * @return objectos encontrados
     */
    public abstract List<M> getAll(HttpSession session);

    /**
     * Informacion adicional que puede necesitar la vista ademas de los objetos
     *
     * @param object objeto referencia
     * @return informacion
     */
    public abstract Map<String,String> otherData(M object);
}
