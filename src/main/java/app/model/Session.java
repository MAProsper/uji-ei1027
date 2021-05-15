package app.model;

import app.model.generic.Model;

public class Session extends Model {
    public String identification;
    public String password;

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Session{" +
                "identification='" + identification + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }
}
