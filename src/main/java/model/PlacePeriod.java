package model;

public class PlacePeriod {
    private int id;
    private int place;

    public PlacePeriod(){

    }

    @Override
    public String toString() {
        return "PlacePeriod{" +
                "id=" + id +
                ", place=" + place +
                '}';
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
