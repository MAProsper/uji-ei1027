package app.model.generic;

import app.util.StringUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

public abstract class Signable extends Model implements Activeable {
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime signUp;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
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
        return signDown == null;
    }

    public String toSignString() {
        return StringUtil.toIntervalString(signUp, signDown);
    }

    @Override
    public Set<String> getFinal() {
        return StringUtil.setJoin(super.getFinal(), "signUp", "signDown");
    }

    @Override
    public String toString() {
        return "Signable{" +
                "signUp=" + signUp +
                ", signDown=" + signDown +
                "} " + super.toString();
    }
}
