package app.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Service {
    public int id;
    public int serviceType;
    public int area;
    public LocalDate scheduleStart;
    public LocalDate scheduleEnd;
    public LocalTime periodStart;
    public LocalTime periodEnd;  // Importante: admite *null* (periodEnd = null ==> El Servicio es indefinido)

    public Service() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

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
        return  "id=" + id +
                ", serviceType=" + serviceType +
                ", area=" + area +
                ", scheduleStart=" + scheduleStart +
                ", scheduleEnd=" + scheduleEnd +
                ", periodStart=" + periodStart +
                ", periodEnd=" + periodEnd;
    }
}
