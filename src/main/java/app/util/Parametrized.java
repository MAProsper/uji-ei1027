package app.util;

import java.lang.reflect.ParameterizedType;

public abstract class Parametrized<T> {
    @SuppressWarnings("unchecked")
    protected Class<T> getParametrizedType() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }
}
