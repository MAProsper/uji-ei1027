package model;

public class AreaCharacteristic {
    private int id;
    private int area;

    public AreaCharacteristic(){

    }

    @Override
    public String toString() {
        return "AreaCharacteristic{" +
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
