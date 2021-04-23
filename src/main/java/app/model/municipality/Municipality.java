package app.model.municipality;

public class Municipality {
    public int place;

    public Municipality() {

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
