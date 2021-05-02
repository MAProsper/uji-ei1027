package app.service.generic;

import app.dao.generic.Dao;
import app.model.generic.Model;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class Service<M extends Model> {
    @Autowired protected Dao<M> dao;

    public Dao<M> getDao() {
        return dao;
    }

    public abstract List<M> getAll(HttpSession session);

    public abstract List<String> getColumnNames();

    public abstract List<Object> mapRow(M object);
}
