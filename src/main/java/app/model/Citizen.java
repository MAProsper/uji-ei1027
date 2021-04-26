package app.model;


public class Citizen extends Person {

    public String mail;
    public String country;  // TODO: hacer un listado de todos los paises
    public String town;
    public String address;

    public Citizen(){
        super();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        return super.toString() +
                ", mail='" + mail + '\'' +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", address='" + address + '\'' ;
    }
}
