package app.model;

import app.model.generic.Scheduleable;

import java.time.LocalDate;
import java.time.LocalTime;

public class AreaPeriod extends Scheduleable {
    public int area;

    public AreaPeriod(){
        super();
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}
