package app.model;

import app.model.generic.Model;
import app.util.StringUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class Reservation extends Model {
    public int areaPeriod;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate date;
    public int citizen;
    public int occupied;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalTime enter;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalTime exit;

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

    public String toPeriodString() {
        return StringUtil.toIntervalString(enter, exit);
    }

    public boolean isActive() {
        return enter != exit;
    }

    @Override
    public Set<String> getFinal() {
        return StringUtil.setJoin(super.getFinal(), "areaPeriod", "citizen");
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "areaPeriod=" + areaPeriod +
                ", date=" + date +
                ", citizen=" + citizen +
                ", occupied=" + occupied +
                ", enter=" + enter +
                ", exit=" + exit +
                "} " + super.toString();
    }
}
