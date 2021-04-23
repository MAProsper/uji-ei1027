package app.model.zone;

public class Zone {
    public int place;
    public int capacity;

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Zone() {
    }


    @Override
    public String toString() {
        return "Zone{" +
                "place=" + place +
                ", capacity=" + capacity +
                '}';
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}