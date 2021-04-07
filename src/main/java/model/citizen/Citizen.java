package model.citizen;

public class Citizen {
    private int person;

    public Citizen() {
    }

    public int getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "person=" + person +
                '}';
    }

    public void setPerson(int person) {
        this.person = person;
    }
}
