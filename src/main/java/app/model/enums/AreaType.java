package app.model.enums;

public enum AreaType {
    PLAYA("Playa"),
    RIO("Rio"),
    ESTANQUE("Estanque"),
    LAGO("Lago"),
    BOSQUE("Bosque"),
    OTRO("Otro");

    private final String name;

    AreaType(final String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
