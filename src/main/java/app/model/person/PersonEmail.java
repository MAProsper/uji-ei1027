package app.model.person;

public class PersonEmail {
    public int id;
    public int person;
    public String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "PersonEmail{" +
                "id=" + id +
                ", person=" + person +
                ", email='" + email + '\'' +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonEmail() {

    }
}
