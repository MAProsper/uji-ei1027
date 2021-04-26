package app.model;

import java.time.LocalDateTime;

public abstract class Place {
    public int id;
    public String name;
    public LocalDateTime signUp;
    public LocalDateTime signDowm; // Importante: admite *null* (si no se ha dado de baja)

    public Place() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDateTime getSignUp() {
        return signUp;
    }

    public void setSignUp(LocalDateTime signUp) {
        this.signUp = signUp;
    }

    public LocalDateTime getSignDowm() {
        return signDowm;
    }

    public void setSignDowm(LocalDateTime signDowm) {
        this.signDowm = signDowm;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' +
                ", signUp=" + signUp +
                ", signDowm=" + signDowm;
    }
}
