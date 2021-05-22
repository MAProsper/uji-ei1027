package app.model;

import app.model.enums.Country;
import app.model.generic.Person;

public class Citizen extends Person {
    public int country;
    public String city;
    public String address;

    public Country getCountry() {
        return Country.values()[country];
    }

    public void setCountry(Country country) {
        this.country = country.ordinal();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "country=" + getCountry() +
                ", town='" + city + '\'' +
                ", address='" + address + '\'' +
                "} " + super.toString();
    }
}
