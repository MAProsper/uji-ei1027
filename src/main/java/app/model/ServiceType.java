package app.model;

public class ServiceType {
    public int id;
    public String name;

    public ServiceType() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'';
    }
}
