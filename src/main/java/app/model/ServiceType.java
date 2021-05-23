package app.model;

import app.model.generic.Signable;

public class ServiceType extends Signable {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ServiceType{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
