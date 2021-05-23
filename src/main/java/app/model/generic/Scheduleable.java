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
        return getPeriodStart().isBefore(other.getPeriodEnd()) && getPeriodEnd().isBefore(other.getPeriodStart());
    }

    public String toPeriodString() {
        return StringUtil.toIntervalString(getPeriodStart(), getPeriodEnd());
    }

    public String toScheduleString() {
        return StringUtil.toIntervalString(getScheduleStart(), getScheduleEnd());
    }

    public boolean isActive() {
        LocalDate now = LocalDate.now();
        return scheduleStart.compareTo(now) >= 0 && (scheduleEnd == null || scheduleEnd.compareTo(now) <= 0);
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

    /**
     * Se puede actualizar si no ha finalizado
     *
     * @return si es actualizable
     */
    public boolean isUpdateable() {
        LocalDate now = LocalDate.now();
        return scheduleEnd == null || scheduleEnd.compareTo(now) >= 0;
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
