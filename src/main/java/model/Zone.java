package model;

import java.time.LocalDateTime;

public final class Zone {
    private int place;

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Zone(){
    }

    @Override
    public String toString() {
        return "Zone{" +
                "place=" + place +
                '}';
    }
}