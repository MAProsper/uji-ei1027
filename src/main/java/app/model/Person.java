package app.model;

public abstract class Person extends Signble {
    public int id;
    public String identification;
    public String name;
    public String password;

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

    @Override
    public String toString() {
        return  "id=" + id +
                ", identification='" + identification + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' + super.toString();
    }
}
