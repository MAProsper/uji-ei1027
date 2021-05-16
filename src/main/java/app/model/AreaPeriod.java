package app.model;

import app.model.generic.Scheduleable;
import app.util.StringUtil;

import java.util.Set;

public class AreaPeriod extends Scheduleable {
    public int area;

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public Set<String> getFinal() {
        return StringUtil.setJoin(super.getFinal(), "area");
    }

    @Override
    public String toString() {
        return "AreaPeriod{" +
                "area=" + area +
                "} " + super.toString();
    }
}
