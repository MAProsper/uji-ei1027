package app.model;

import app.model.generic.Model;
import org.springframework.stereotype.Service;

@Service
public class ServiceType extends Model {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ServiceType{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
