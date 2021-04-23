package app.model.citizen;

public class Citizen {
    public int person;

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
