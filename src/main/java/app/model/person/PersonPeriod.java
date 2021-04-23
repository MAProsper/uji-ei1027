package app.model.person;

public class PersonPeriod {
    public int id;
    public int person;
    public int period;

    public PersonPeriod() {
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "PersonPeriod{" +
                "id=" + id +
                ", person=" + person +
                ", period=" + period +
                '}';
    }
}
