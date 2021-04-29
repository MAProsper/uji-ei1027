package app.dao.generic;

import app.model.generic.Signable;

import java.time.LocalDateTime;

public abstract class SignableDao<T extends Signable> extends Dao<T> {
    @Override
    public void add(T object) {
        object.setSingUp(LocalDateTime.now());
        super.add(object);
    }

    @Override
    public void update(T object) {
        T prev = getById(object.getId());
        object.setSingUp(prev.getSingUp());
        object.setSingDown(prev.getSingDown());
        super.update(object);
    }

    @Override
    public void delete(int id) {
        T object = getById(id);
        object.setSingDown(LocalDateTime.now());
        super.update(object);
    }
}
