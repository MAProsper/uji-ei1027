package app.util;

import org.springframework.core.NestedRuntimeException;

public class SanaException extends NestedRuntimeException {
    public SanaException(String msg) {
        super(msg);
    }

    public SanaException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
