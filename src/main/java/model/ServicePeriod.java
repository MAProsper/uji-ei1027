package model;

public final class ServicePeriod {
    private int id;
    private int service;

    public ServicePeriod(){

    }

    @Override
    public String toString() {
        return "ServicePeriod{" +
                "id=" + id +
                ", service=" + service +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }
}
