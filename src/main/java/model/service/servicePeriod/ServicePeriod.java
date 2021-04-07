package model.service.servicePeriod;

public class ServicePeriod {
    private int id;
    private int service;
    private int period;

    public ServicePeriod(){

    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "ServicePeriod{" +
                "id=" + id +
                ", service=" + service +
                ", period=" + period +
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
