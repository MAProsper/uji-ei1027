package app.model.generic;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public abstract class Signable extends Model {
    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
    public LocalDateTime signUp;
    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
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

    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        return signUp.isBefore(now) && (signDown == null || signDown.isAfter(now));
    }

    @Override
    public String toString() {
        return "Signable{" +
                "signUp=" + signUp +
                ", signDown=" + signDown +
                "} " + super.toString();
    }
}
