package app.model;

import app.model.generic.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation extends Model {
    public int code;
    public int areaPeriod;
    public LocalDate date;
    public int citizen;
    public int occupied;
    public LocalTime enter;
    public LocalTime exit;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getAreaPeriod() {
        return areaPeriod;
    }

    public void setAreaPeriod(int areaPeriod) {
        this.areaPeriod = areaPeriod;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCitizen() {
        return citizen;
    }

    public void setCitizen(int citizen) {
        this.citizen = citizen;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public LocalTime getEnter() {
        return enter;
    }

    public void setEnter(LocalTime enter) {
        this.enter = enter;
    }

    public LocalTime getExit() {
        return exit;
    }

    public void setExit(LocalTime exit) {
        this.exit = exit;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "citizen=" + citizen +
                ", areaPeriod=" + areaPeriod +
                ", code=" + code +
                ", date=" + date +
                ", occupied=" + occupied +
                ", enter=" + enter +
                ", exit=" + exit +
                "} " + super.toString();
    }
}
