package app.model;

import app.model.generic.Model;
import org.springframework.stereotype.Service;

@Service
public class ServiceType extends Model {
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
        return "ServiceType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
