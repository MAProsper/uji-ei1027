package app.model;

import app.model.enums.AreaType;

public class Area extends Place {

    public int municipality; // id del municipio
    public AreaType type;  // TODO: Hacer esto correctamente
    public String description;
    public String location;

    public Area() {
        super();
    }

    public AreaType getType() {
        return type;
    }

    public void setType(AreaType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return super.toString() + "," +
                " municipality='" + municipality + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'';
    }
}
