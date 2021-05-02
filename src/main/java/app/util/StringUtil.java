package app.util;

import java.time.temporal.Temporal;

public class StringUtil {
    public static String toString(Object object) {
        return object != null ? object.toString() : "Sin definir";
    }

    public static String toIntervalString(Temporal start, Temporal end) {
        return String.format("%s - %s", toString(start), toString(end));
    }
}
