package app.model;

import app.model.generic.Model;

public class Session extends Model {
    public String identification;
    public String password;
    public String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Session{" +
                "identification='" + identification + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                "} " + super.toString();
    }
}
