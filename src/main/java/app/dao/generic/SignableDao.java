package app.dao.generic;

import app.model.generic.Signable;

import java.time.LocalDateTime;

/**
 * Como el otro DAO, pero:
 * - si se añade, se cambia el signUp por la fecha del alta.
 * - si se borra, se cambia el signDown por la fecha del borrado.
 */
public abstract class SignableDao<T extends Signable> extends Dao<T> {
    //TODO: mirar si hacer la "fijación" de hora en el controlador

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
