package app.dao.generic;

import app.model.generic.Signable;

import java.time.LocalDateTime;

public abstract class SignableDao<T extends Signable> extends Dao<T> {
    @Override
    public void add(T object) { //TODO: comprobar que no instancas activas (salta excepcion por los UNIQUE) (coprobacion basada en UNIQUE solo posible en daos final?)
        object.setSingUp(LocalDateTime.now());
        super.add(object);
    }

    @Override
    public void delete(T object) {
        object.setSingDown(LocalDateTime.now());
        super.update(object);
    }
}
