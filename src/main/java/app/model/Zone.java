package app.model;

import app.model.generic.Place;

public class Zone extends Place {

    public int area; // id del área asociada
    public int capacity;

    public Zone() {
        super();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "area=" + area +
                ", capacity=" + capacity +
                "} " + super.toString();
    }
}
