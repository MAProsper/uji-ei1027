package app.model.enums;

public enum AreaType {
    PLAYA("Playa"),
    RIO("Río"),
    ESTANQUE("Estanque"),
    LAGO("Lago"),
    BOSQUE("Bosque"),
    OTRO("Otro");

    private final String name;

    AreaType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
