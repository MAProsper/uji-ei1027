package model;

import java.time.LocalDateTime;

public class Zona {
    private String location;
    private int maximumCapacity;
    private LocalDateTime signUpDate;
    private LocalDateTime signDownDate;

    public Zona() {
    }

    public String getLocation() {
        return location;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public LocalDateTime getSignUpDate() {
        return signUpDate;
    }

    public LocalDateTime getSignDownDate() {
        return signDownDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public void setSignUpDate(LocalDateTime signUpDate) {
        this.signUpDate = signUpDate;
    }

    public void setSignDownDate(LocalDateTime signDownDate) {
        this.signDownDate = signDownDate;
    }

    @Override
    public String toString() {
        return "Zona{" +
                "location='" + location + '\'' +
                ", maximumCapacity=" + maximumCapacity +
                ", signUpDate=" + signUpDate +
                ", signDownDate=" + signDownDate +
                '}';
    }
}
