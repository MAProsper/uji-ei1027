package app.model.reservation;

public class ReservationZone {
    public int id;
    public int reservation;
    public int zone;

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

    public ReservationZone() {
    }

    @Override
    public String toString() {
        return "ReservationZone{" +
                "id=" + id +
                ", reservation=" + reservation +
                ", zone=" + zone +
                '}';
    }
}