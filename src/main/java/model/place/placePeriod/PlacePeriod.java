package model.place.placePeriod;

public class PlacePeriod {
    private int id;
    private int place;
    private int period;

    public PlacePeriod(){
    }

    @Override
    public String toString() {
        return "PlacePeriod{" +
                "id=" + id +
                ", place=" + place +
                ", period=" + period +
                '}';
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
