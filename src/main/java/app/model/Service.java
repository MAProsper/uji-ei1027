package app.model;

import app.model.generic.Scheduleable;
import app.util.StringUtil;

import java.util.Set;

public class Service extends Scheduleable {
    public int area;
    public int serviceType;

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
    public Set<String> getFinal() {
        return StringUtil.setJoin(super.getFinal(), "area");
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceType=" + serviceType +
                ", area=" + area +
                "} " + super.toString();
    }
}
