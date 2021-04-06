package model;

public class PersonResidence {
    private int id;
    private int person;

    public PersonResidence(){

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

    @Override
    public String toString() {
        return "PersonResidence{" +
                "id=" + id +
                ", person=" + person +
                '}';
    }
}
