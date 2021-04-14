package app.model.person;

public class PersonPhone {
    private int id;
    private int person;
    private String phone;

    public PersonPhone() {
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "PersonPhone{" +
                "id=" + id +
                ", person=" + person +
                ", phone='" + phone + '\'' +
                '}';
    }
}
