package app.model.generic;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Scheduleable extends Model {
    public LocalDate scheduleStart;
    public LocalDate scheduleEnd;
    public LocalTime periodStart;
    public LocalTime periodEnd; // Importante: **NO **admite *null*

    public LocalDate getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(LocalDate scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public LocalDate getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(LocalDate scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }

    public LocalTime getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalTime periodStart) {
        this.periodStart = periodStart;
    }

    public LocalTime getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(LocalTime periodEnd) {
        this.periodEnd = periodEnd;
    }

    @Override
    public String toString() {
        return "Scheduleable{" +
                "scheduleStart=" + scheduleStart +
                ", scheduleEnd=" + scheduleEnd +
                ", periodStart=" + periodStart +
                ", periodEnd=" + periodEnd +
                "} " + super.toString();
    }
}
