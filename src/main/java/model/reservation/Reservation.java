package model.reservation;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private int citizen;
    private int code;
    private int occupancy;
    private int areaPeriod;
    private LocalDateTime day;

    public Reservation() {
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", citizen=" + citizen +
                ", code=" + code +
                ", occupancy=" + occupancy +
                ", areaPeriod=" + areaPeriod +
                ", day=" + day +
                '}';
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }

    public LocalDateTime getDay() {
        return day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCitizen() {
        return citizen;
    }

    public void setCitizen(int citizen) {
        this.citizen = citizen;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getAreaPeriod() {
        return areaPeriod;
    }

    public void setAreaPeriod(int areaPeriod) {
        this.areaPeriod = areaPeriod;
    }
}
