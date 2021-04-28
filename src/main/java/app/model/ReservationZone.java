package app.model;

import app.model.generic.Model;

public class ReservationZone extends Model {
    public int reservation;
    public int zone;

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "ReservationZone{" +
                "reservation=" + reservation +
                ", zone=" + zone +
                "} " + super.toString();
    }
}
