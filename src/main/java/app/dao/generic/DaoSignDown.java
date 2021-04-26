package app.dao.generic;

//TODO: Buscar nombre mejor

import app.model.SignDowneable;

import java.time.LocalDateTime;

/**
 * Como el otro DAO, pero:
 *      - si se añade y ya estaba dado de alta, acutualiza.
 *      - si se borra, se cambia el signDown por la fecha del borrado.
 */
public class DaoSignDown extends Dao<SignDowneable>{

    public DaoSignDown(){
        super(SignDowneable.class);
    }

    //TODO: mirar si hacer la "fijación" de hora en el controlador

    @Override
    public void add(SignDowneable object){
        object.setSingUp(LocalDateTime.now());
        super.update(object);
    }

    @Override
    public void delete(SignDowneable object){
        object.setSingDown( LocalDateTime.now() );
        super.update(object);
    }

}
