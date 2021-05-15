package app.validator.generic;

import java.util.function.BiConsumer;

public interface FieldError extends BiConsumer<String,String> {
}
