package app.model;

import java.time.LocalDateTime;

public abstract class Person {
    public int id;
    public String identification;
    public String name;
    public String password;
    public LocalDateTime singUp;
    public LocalDateTime singDown;  //

    public Person() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getSingUp() {
        return singUp;
    }

    public LocalDateTime getSingDown() {
        return singDown;
    }

    public void setSingUp(LocalDateTime singUp) {
        this.singUp = singUp;
    }

    public void setSingDown(LocalDateTime singDown) {
        this.singDown = singDown;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", identification='" + identification + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", singUp=" + singUp +
                ", singDown=" + singDown ;
    }
}
