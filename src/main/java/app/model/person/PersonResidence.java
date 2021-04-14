package app.model.person;

public class PersonResidence {
    private int id;
    private int person;
    private int address;

    public PersonResidence() {
        super();
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

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonResidence{" +
                "id=" + id +
                ", person=" + person +
                ", address=" + address +
                '}';
    }
}
