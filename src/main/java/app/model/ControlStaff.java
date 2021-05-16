package app.model;

import app.model.generic.Person;
import app.util.StringUtil;

import java.util.Set;

public class ControlStaff extends Person {
    public int areaPeriod;

    public int getAreaPeriod() {
        return areaPeriod;
    }

    public void setAreaPeriod(int areaPeriod) {
        this.areaPeriod = areaPeriod;
    }

    @Override
    public Set<String> getFinal() {
        return StringUtil.setJoin(super.getFinal(), "areaPeriod");
    }

    @Override
    public String toString() {
        return "ControlStaff{" +
                "areaPeriod=" + areaPeriod +
                "} " + super.toString();
    }
}
