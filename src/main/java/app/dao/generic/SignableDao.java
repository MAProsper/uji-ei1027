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
    public void delete(T object) {
        object.setSingDown(LocalDateTime.now());
        super.update(object);
    }

}
