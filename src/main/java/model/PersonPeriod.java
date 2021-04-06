package model;

public class PersonPeriod {
    private int id;
    private int person;

    @Override
    public String toString() {
        return "PersonPeriod{" +
                "id=" + id +
                ", person=" + person +
                '}';
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

    public PersonPeriod(){

    }
}
