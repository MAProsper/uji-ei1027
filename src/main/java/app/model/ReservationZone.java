package app.model;

import app.model.generic.Model;

public class ReservationZone extends Model {
    public int id;
    public int reservation;
    public int zone;

    public ReservationZone() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
