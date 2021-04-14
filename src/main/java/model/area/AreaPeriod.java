package model.area;

public class AreaPeriod {
    private int id;
    private int area;
    private int period;

    public AreaPeriod() {
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

    public int getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "AreaPeriod{" +
                "id=" + id +
                ", area=" + area +
                ", period=" + period +
                '}';
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
