package app.model.reservation;

public class ReservationPeriod {
    public int id;
    public int reservation;
    public int period;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "ReservationPeriod{" +
                "id=" + id +
                ", reservation=" + reservation +
                ", period=" + period +
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

    public ReservationPeriod() {

    }
}
