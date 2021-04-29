package app.model;

import app.model.generic.Person;

public class MunicipalManager extends Person {
    public int municipality;
    public String phone;

    public int getMunicipality() {
        return municipality;
    }

    public void setMunicipality(int municipality) {
        this.municipality = municipality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MunicipalManager{" +
                "phone='" + phone + '\'' +
                ", municipality=" + municipality +
                "} " + super.toString();
    }
}
