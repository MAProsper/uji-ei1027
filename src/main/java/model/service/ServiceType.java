package model.service;

public class ServiceType {
    private int id;
    private String type;
    private String description;

    public ServiceType() {
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ServiceType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
