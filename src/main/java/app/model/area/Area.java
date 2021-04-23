package app.model.area;

public class Area {
    private int place;
    private int municipality;
    private String description;
    private double length;
    private double width;

    public Area() {
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getMunicipality() {
        return municipality;
    }

    public void setMunicipality(int municipality) {
        this.municipality = municipality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Area{" +
                "place=" + place +
                ", municipality=" + municipality +
                ", description='" + description + '\'' +
                ", lenght=" + length +
                ", width=" + width +
                '}';
    }
}
