package app.model.generic;

public interface Activeable {
    boolean isActive();

    static boolean isActive(Activeable object) {
        return object != null && object.isActive();
    }
}
