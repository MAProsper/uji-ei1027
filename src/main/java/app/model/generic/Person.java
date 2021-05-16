package app.model.generic;

import app.util.StringUtil;

import java.util.Set;

public abstract class Person extends Signable {
    public String identification;
    public String name;
    public String mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toIdentificationString() {
        return String.format("%s (%s)", name, identification);
    }

    @Override
    public Set<String> getFinal() {
        return StringUtil.setJoin(super.getFinal(), "identification");
    }

    @Override
    public String toString() {
        return "Person{" +
                "identification='" + identification + '\'' +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }
}
