package app.model.municipalManager;

public class MunicipalManager {
    private int person;

    public MunicipalManager() {
    }

    @Override
    public String toString() {
        return "MunicipalManager{" +
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
