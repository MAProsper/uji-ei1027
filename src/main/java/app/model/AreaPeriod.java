package app.model;

import app.model.generic.Scheduleable;

public class AreaPeriod extends Scheduleable {
    public int area;

    public AreaPeriod() {
        super();
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "AreaPeriod{" +
                "area=" + area +
                "} " + super.toString();
    }
}
