package model;

public class ReservationPeriod {
    private int id;
    private int reservation;

    @Override
    public String toString() {
        return "ReservationPeriod{" +
                "id=" + id +
                ", reservation=" + reservation +
                '}';
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

    public ReservationPeriod(){

    }
}
