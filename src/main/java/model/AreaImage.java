package model;

public class AreaImage {
    private int id;
    private int area;

    @Override
    public String toString() {
        return "AreaImage{" +
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

    public AreaImage(){

    }
}
