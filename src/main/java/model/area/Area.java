package model.area;

public class Area {
    private int place;
    private int municipality;
    private String description;
    private double lenght;
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

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
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
                ", lenght=" + lenght +
                ", width=" + width +
                '}';
    }
}
