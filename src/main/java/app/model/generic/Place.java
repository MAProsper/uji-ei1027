package app.model.generic;

public abstract class Place extends Signable {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
