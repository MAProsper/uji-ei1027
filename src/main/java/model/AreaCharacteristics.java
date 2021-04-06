package model;

public class AreaCharacteristics {
    private int id;
    private int area;

    public AreaCharacteristics(){

    }

    @Override
    public String toString() {
        return "AreaCharacteristics{" +
                "id=" + id +
                ", area=" + area +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}
