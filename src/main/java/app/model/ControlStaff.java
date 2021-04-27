package app.model;

import app.model.generic.Person;

public class ControlStaff extends Person {
    public int areaPeriod;

    public ControlStaff() {
        super();
    }

    public int getAreaPeriod() {
        return areaPeriod;
    }

    public void setAreaPeriod(int areaPeriod) {
        this.areaPeriod = areaPeriod;
    }

    @Override
    public String toString() {
        return "ControlStaff{" +
                "areaPeriod=" + areaPeriod +
                "} " + super.toString();
    }
}
