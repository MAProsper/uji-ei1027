package app.model;

import app.model.generic.Scheduleable;

public class Service extends Scheduleable {

    public int serviceType;
    public int area;

    public Service() {
        super();
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceType=" + serviceType +
                ", area=" + area +
                "} " + super.toString();
    }
}
