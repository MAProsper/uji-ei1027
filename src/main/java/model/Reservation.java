package model;

public final class Reservation {
    private int id;
    private int citizen;
    private int code;
    private int occupancy;
    private int areaPeriod;

    public Reservation() {
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
