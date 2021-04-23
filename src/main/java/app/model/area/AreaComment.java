package app.model.area;

public class AreaComment {
    public int id;
    public int area;
    public String comment;

    public AreaComment() {
    }

    @Override
    public String toString() {
        return "AreaComment{" +
                "id=" + id +
                ", area=" + area +
                ", comment='" + comment + '\'' +
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}