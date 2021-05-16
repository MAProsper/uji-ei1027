package app.util;

import java.time.temporal.Temporal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtil {
    public static String toString(Object object) {
        String text = object != null ? object.toString() : "";
        return text.isBlank() ? "Sin definir" : text;
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

    public static String formatJoin(String format, Set<String> values) {
        return values.stream().map(column -> String.format(format, column)).collect(Collectors.joining(", "));
    }

    public static Set<String> setJoin(Set<String> base, String... other) {
        return Stream.of(base, Set.of(other)).flatMap(Set::stream).collect(Collectors.toSet());
    }
}
