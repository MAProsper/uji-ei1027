package app.model;

public abstract class Place extends Signble {
    public int id;
    public String name;


    public Place() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' + super.toString();
    }
}
