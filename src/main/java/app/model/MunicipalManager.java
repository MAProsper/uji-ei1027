package app.model;

import app.model.generic.Person;

public class MunicipalManager extends Person {
    public String phone;
    public int municipality;

    public String getPhone() {
        return phone;
    }

    public int getMunicipality() {
        return municipality;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMunicipality(int municipality) {
        this.municipality = municipality;
    }

    @Override
    public String toString() {
        return "MunicipalManager{" +
                "phone='" + phone + '\'' +
                ", municipality=" + municipality +
                "} " + super.toString();
    }
}