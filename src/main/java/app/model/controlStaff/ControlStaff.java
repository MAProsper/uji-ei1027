package app.model.controlStaff;

public class ControlStaff {
    public int person;

    public ControlStaff() {

    }

    @Override
    public String toString() {
        return "ControlStaff{" +
                "person=" + person +
                '}';
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }
}
