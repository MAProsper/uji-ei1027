package model;

public final class MunicipalManager {
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
