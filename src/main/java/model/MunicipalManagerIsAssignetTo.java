package model;

public class MunicipalManagerIsAssignetTo {
    private int id;
    private int municipality;

    public MunicipalManagerIsAssignetTo(){

    }

    @Override
    public String toString() {
        return "MunicipalManagerIsAssignetTo{" +
                "id=" + id +
                ", municipality=" + municipality +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMunicipality() {
        return municipality;
    }

    public void setMunicipality(int municipality) {
        this.municipality = municipality;
    }
}
