package model;

public final class Municipality {
    private int place;

    public Municipality(){

    }

    @Override
    public String toString() {
        return "Municipality{" +
                "place=" + place +
                '}';
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
