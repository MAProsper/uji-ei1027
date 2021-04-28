package app.model.generic;

import java.time.LocalDateTime;

//TODO: Buscar un nombre mejor para la clase: es un poco horrible, pero no se me ocurre otro mejor.

/**
 * *Abstracción*: para hacer un DAO más simple que, al borrar en realidad ponga la fecha de baja en vez de borrar.
 */
public abstract class Signable extends Model {
    public LocalDateTime signUp;
    public LocalDateTime signDown; // Importante: admite *null* (si no se ha dado de baja)

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getSingUp() {
        return this.signUp;
    }

    public LocalDateTime getSingDown() {
        return this.signDown;
    }

    public void setSingUp(LocalDateTime singUp) {
        this.signUp = singUp;
    }

    public void setSingDown(LocalDateTime singDown) {
        this.signDown = singDown;
    }

    @Override
    public String toString() {
        return "Signable{" +
                "signUp=" + signUp +
                ", signDown=" + signDown +
                "} " + super.toString();
    }
}
