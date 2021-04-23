package app.util;

import com.jayway.jsonpath.spi.mapper.MappingException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReflectUtil<T> {
    protected Class<T> cls;
    protected Map<String, Field> fields;

    public ReflectUtil(Class<T> cls) {
        this.cls = cls;
        fields = new HashMap<>();
        for (Field field : cls.getDeclaredFields()) {
            fields.put(field.getName(), field);
        }
    }

    public T newInstance() {
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new MappingException(e.getMessage());
        }
    }

    public Set<String> getFields() {
        return fields.keySet();
    }

    public void set(T object, String name, Object value) {
        try {
            fields.get(name).set(object, value);
        } catch (IllegalAccessException e) {
            throw new MappingException(e.getMessage());
        }
    }

    public Object get(T object, String name) {
        try {
            return fields.get(name).get(object);
        } catch (IllegalAccessException e) {
            throw new MappingException(e.getMessage());
        }
    }


}
