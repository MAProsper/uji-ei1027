package app.model;

import app.model.generic.Model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reservation extends Model {
    public int id;
    public int citizen;
    public int areaPeriod;
    public int code;
    public LocalDateTime date;
    public int occupied;
    public LocalTime enter;
    public LocalTime exit;

    public Reservation() {
        super();
    }

   public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
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

    public int getAreaPeriod() {
        return areaPeriod;
    }

    public void setAreaPeriod(int areaPeriod) {
        this.areaPeriod = areaPeriod;
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
        return  "id=" + id +
                ", citizen=" + citizen +
                ", areaPeriod=" + areaPeriod +
                ", code=" + code +
                ", date=" + date +
                ", occupied=" + occupied +
                ", enter=" + enter +
                ", exit=" + exit;
    }
}
