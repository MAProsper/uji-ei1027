package app.dao.generic;

import app.model.generic.Signable;

import java.time.LocalDateTime;

/**
 * Como el otro DAO, pero:
 *      - si se añade y ya estaba dado de alta, acutualiza.
 *      - si se borra, se cambia el signDown por la fecha del borrado.
 */
public abstract class SignableDao<T extends Signable> extends Dao<T>{
    public SignableDao(Class<T> cls) {
        super(cls);
    }

    public SignableDao(Mapper<T> mapper) {
        super(mapper);
    }

    //TODO: mirar si hacer la "fijación" de hora en el controlador

    @Override
    public void add(T object){
        object.setSingUp(LocalDateTime.now());
        super.update(object);
    }

    @Override
    public void delete(T object){
        object.setSingDown( LocalDateTime.now() );
        super.update(object);
    }

}
