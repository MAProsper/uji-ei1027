package app.dao.generic;

import app.model.generic.Signable;

import java.time.LocalDateTime;
import java.util.List;

public abstract class SignableDao<T extends Signable> extends Dao<T> {
    public List<T> getActive() {
        return getByField("signDown", null);
    }

    @Override
    public void add(T object) {
        object.setSingUp(LocalDateTime.now());
        super.add(object);
    }

    @Override
    public void delete(int id) {
        T object = getById(id);
        object.setSingDown(LocalDateTime.now());
        update(object);
    }
}
