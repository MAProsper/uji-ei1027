package app.model;

import app.model.enums.AreaType;
import app.model.generic.Place;

public class Area extends Place {

    public int municipality; // id del municipio
    public int type;  // TODO: Hacer esto correctamente
    public String description;
    public String location;
    public String image;

    public Area() {
        super();
    }

    public AreaType getType() {
        return AreaType.values()[type];
    }

    public void setType(AreaType type) {
        this.type = type.ordinal();
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

    public int getMunicipality() {
        return municipality;
    }

    public void setMunicipality(int municipality) {
        this.municipality = municipality;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Area{" +
                "municipality=" + municipality +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", image='" + image + '\'' +
                "} " + super.toString();
    }
}
