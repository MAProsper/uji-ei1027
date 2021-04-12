package model.municipalManager.municipalManagerIsAssignedTo;

public class MunicipalManagerIsAssignetTo {
    private int id;
    private int municipality;
    private int municipalManager;
    private int period;

    public MunicipalManagerIsAssignetTo() {

    }

    public int getMunicipalManager() {
        return municipalManager;
    }

    public void setMunicipalManager(int municipalManager) {
        this.municipalManager = municipalManager;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "MunicipalManagerIsAssignedTo{" +
                "id=" + id +
                ", municipality=" + municipality +
                ", municipalManager=" + municipalManager +
                ", period=" + period +
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