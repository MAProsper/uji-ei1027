package app.model.area;

public class AreaCharacteristic {
    public int id;
    public int area;
    public String characteristic;

    public AreaCharacteristic() {
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