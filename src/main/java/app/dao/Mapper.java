package app.dao;

import com.jayway.jsonpath.spi.mapper.MappingException;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Mapper<T> implements RowMapper<T> {
    protected Class<T> cls;
    protected Map<String, Method> getters;
    protected Map<String, Method> setters;

    public Mapper(String clsName) {
        try {
            cls = (Class<T>) Class.forName(clsName);
        } catch (ClassNotFoundException e) {
            throw new MappingException(e.getMessage());
        }

        getters = new HashMap<>();
        setters = new HashMap<>();
        Method[] methods = cls.getMethods();
        Map<String, Map<String, Method>> types = Map.of("get", getters, "set", setters);

        for (Method method : methods) {
            String name = method.getName();
            String type = name.replaceFirst("^([a-z]+).+$", "$1");
            if (types.containsKey(type)) types.get(type).put(toDatabase(name), method);
        }
    }

    protected T newInstance() {
        try {
            return cls.getConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new MappingException(e.getMessage());
        }
    }

    protected Object invoke(T object, Method method, Object... args) {
        try {
            return method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new MappingException(e.getMessage());
        }
    }

    protected String toDatabase(String string) {
        return string.replaceFirst("^[a-z]+", "").replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    public void set(T object, String name, Object value) {
        if (value instanceof Timestamp) value = ((Timestamp) value).toLocalDateTime();
        invoke(object, setters.get(name), value);
    }

    public Object get(T object, String name) {
        return invoke(object, getters.get(name));
    }

    public Object[] getRow(T object) {
        return getNames().stream().map(param -> get(object, param)).toArray();
    }

    public Set<String> getNames() {
        return setters.keySet();
    }

    @Override
    public T mapRow(ResultSet resultSet, int i) throws SQLException {
        T obj = newInstance();
        for (String param : getNames()) set(obj, param, resultSet.getObject(param));
        return obj;
    }
}