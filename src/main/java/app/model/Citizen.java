package app.model;

import app.model.enums.Country;
import app.model.generic.Person;

public class Citizen extends Person {
    public int country;
    public String town;
    public String address;

    public Country getCountry() {
        return Country.values()[country];
    }

    public void setCountry(Country country) {
        this.country = country.ordinal();
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
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
                ", town='" + town + '\'' +
                ", address='" + address + '\'' +
                "} " + super.toString();
    }
}
