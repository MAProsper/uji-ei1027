package app.util;

import java.time.temporal.Temporal;

public class StringUtil {
    public static String toString(Object object) {
        return object != null ? object.toString() : "Sin definir";
    }

    public static String toIntervalString(Temporal start, Temporal end) {
        return String.format("%s - %s", toString(start), toString(end));
    }

    public static String toPackageCase(String text) {
        return Character.toLowerCase(text.charAt(0)) + text.substring(1);
    }

    public static String toUrlCase(String text) {
        return text.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase();
    }

    public static String toDataBaseCase(String text) {
        return text.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }
}
