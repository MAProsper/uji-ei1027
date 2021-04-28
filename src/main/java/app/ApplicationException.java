package app;

import org.springframework.core.NestedRuntimeException;

public class ApplicationException extends NestedRuntimeException {
    public ApplicationException(String msg) {
        super(msg);
    }

    public ApplicationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
