package model;

public final class AreaCharacteristic {
    private int id;
    private int area;
    private String characteristic;

    public AreaCharacteristic(){
    }

    @Override
    public String toString() {
        return "AreaCharacteristic{" +
                "id=" + id +
                ", area=" + area +
                ", characteristic='" + characteristic + '\'' +
                '}';
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
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
