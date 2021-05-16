package app.model;

import app.model.generic.Person;
import app.util.StringUtil;

import java.util.Set;

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
    public Set<String> getFinal() {
        return StringUtil.setJoin(super.getFinal(), "municipality");
    }

    @Override
    public String toString() {
        return "MunicipalManager{" +
                "phone='" + phone + '\'' +
                ", municipality=" + municipality +
                "} " + super.toString();
    }
}
