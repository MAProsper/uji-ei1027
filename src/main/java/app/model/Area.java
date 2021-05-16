package app.model;

import app.model.enums.AreaType;
import app.model.generic.Place;
import app.util.StringUtil;

import java.util.Set;

public class Area extends Place {
    public int municipality; // id del municipio
    public int type;
    public String description;
    public String location;
    public String image;

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
    public Set<String> getFinal() {
        return StringUtil.setJoin(super.getFinal(), "municipality");
    }

    @Override
    public String toString() {
        return "Area{" +
                "municipality=" + municipality +
                ", type=" + getType() +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", image='" + image + '\'' +
                "} " + super.toString();
    }
}
