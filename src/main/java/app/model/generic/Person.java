package app.model.generic;

public abstract class Person extends Signable {
    public String identification;
    public String name;
    public String password;

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
        return "Person{" +
                "identification='" + identification + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }
}