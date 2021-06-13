package app.model.generic;

import app.util.StringUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public abstract class Scheduleable extends Model implements Activeable {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate scheduleStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate scheduleEnd;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalTime periodStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
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
        // this solapa a other si se cumplen uno de los siguientes puntos:
        // - la hora de fin de this está entre las horas de inicio y de fin de other
        // - la hora de inicio de this está entre las horas de inicio y de fin de other
        // - la hora de inicio de this es anterior o igual a la de other y la hora de fin de this es posterior o igual a la de other
        return (getPeriodEnd().compareTo(other.getPeriodStart()) >= 0 && getPeriodEnd().compareTo(other.getPeriodEnd()) <= 0) ||
                (getPeriodStart().compareTo(other.getPeriodStart()) >= 0 && getPeriodStart().compareTo(other.getPeriodEnd()) <= 0) ||
                (getPeriodStart().compareTo(other.getPeriodStart()) <= 0 && getPeriodEnd().compareTo(other.getPeriodEnd()) >= 0);
    }

    public String toPeriodString() {
        return StringUtil.toIntervalString(getPeriodStart(), getPeriodEnd());
    }

    public String toScheduleString() {
        return StringUtil.toIntervalString(getScheduleStart(), getScheduleEnd());
    }

    public boolean isActive() {
        LocalDate now = LocalDate.now();
        return scheduleStart.compareTo(now) <= 0 && (scheduleEnd == null || scheduleEnd.compareTo(now) >= 0);
    }

    @Override
    public Set<String> getFinal() {
        LocalDate now = LocalDate.now();
        Set<String> fixed = super.getFinal();
        return scheduleStart != null && scheduleStart.isBefore(now) ? StringUtil.setJoin(fixed, "scheduleStart") : fixed;
    }

    @Override
    public Set<String> getNullable() {
        return StringUtil.setJoin(super.getNullable(), "scheduleEnd");
    }

    public boolean isEnded() {
        LocalDate now = LocalDate.now();
        return scheduleEnd == null || scheduleEnd.isBefore(now);
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
