package app.model;

import app.model.generic.Place;
import app.util.StringUtil;

import java.util.Set;

public class Zone extends Place {
    public int area; // id del área asociada
    public int capacity;

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String toDisplayName() {
        return String.format("%s (max. %d)", name, capacity);
    }

    @Override
    public Set<String> getFinal() {
        return StringUtil.setJoin(super.getFinal(), "area");
    }

    @Override
    public String toString() {
        return "Zone{" +
                "area=" + area +
                ", capacity=" + capacity +
                "} " + super.toString();
    }
}
