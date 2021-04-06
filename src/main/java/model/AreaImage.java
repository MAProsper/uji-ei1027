package model;

public final class AreaImage {
    private int id;
    private int area;
    private String image;


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "AreaImage{" +
                "id=" + id +
                ", area=" + area +
                ", image='" + image + '\'' +
                '}';
    }
}
