package app.model.generic;

import java.time.LocalDateTime;

//TODO: Buscar un nombre mejor para la clase: es un poco horrible, pero no se me ocurre otro mejor.

/**
 * *Abstracción*: para hacer un DAO más simple que, al borrar en realidad ponga la fecha de baja en vez de borrar.
 */
public abstract class Signble {

    public LocalDateTime signUp;
    public LocalDateTime signDowm; // Importante: admite *null* (si no se ha dado de baja)

    public Signble(){
        super();
    }

    public LocalDateTime getSingUp() {
        return this.signUp;
    }

    public LocalDateTime getSingDown() {
        return this.signDowm;
    }

    public void setSingUp(LocalDateTime singUp) {
        this.signUp = singUp;
    }

    public void setSingDown(LocalDateTime singDown) {
        this.signDowm = singDown;
    }

    @Override
    public String toString(){
        return  ", signUp='"+this.signUp+'\''+
                ", signDown='"+this.signDowm+'\'';
    }

}
