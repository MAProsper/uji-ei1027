package app.model.generic;

import app.util.StringUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.Temporal;

public abstract class Scheduleable extends Model {
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    public LocalDate scheduleStart;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    public LocalDate scheduleEnd;
    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
    public LocalTime periodStart;
    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
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

    public boolean overlapsWith(Scheduleable other) {
        return getPeriodStart().isBefore(other.getPeriodEnd()) && getPeriodEnd().isBefore(other.getPeriodStart());
    }

    public String toPeriodString() {
        return StringUtil.toIntervalString(getPeriodStart(), getPeriodEnd());
    }

    public String toScheduleString() {
        return StringUtil.toIntervalString(getScheduleStart(), getScheduleEnd());
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
