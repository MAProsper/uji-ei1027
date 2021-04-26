package app.model.enums;

public enum AreaType {
    PLAYA("Playa"),
    RIO("Rio"),
    ESTANQUE("Estanque"),
    LAGO("Lago"),
    BOSQUE("Bosque"),
    OTRO("Otro");

    private String type;

    AreaType(final String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
