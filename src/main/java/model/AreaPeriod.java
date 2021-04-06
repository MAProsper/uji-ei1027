package model;

public final class AreaPeriod {
    private int id;
    private int area;

    public AreaPeriod(){

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

    @Override
    public String toString() {
        return "AreaPeriod{" +
                "id=" + id +
                ", area=" + area +
                '}';
    }
}
