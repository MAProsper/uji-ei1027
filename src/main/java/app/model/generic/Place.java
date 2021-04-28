package app.model.generic;

public abstract class Place extends Signable {
    public String name;

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
