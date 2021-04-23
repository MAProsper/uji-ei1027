package app.model.service;

public class Service {
    public int id;
    public int serviceType;
    public int area;

    public Service() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", serviceType=" + serviceType +
                ", area=" + area +
                '}';
    }
}
