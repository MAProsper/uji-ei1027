package app.dao;

import app.util.ReflectUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

public class Mapper<T> implements RowMapper<T> {
    protected ReflectUtil<T> reflect;

    public Mapper(Class<T> cls) {
        reflect = new ReflectUtil<>(cls);
    }

    public Set<String> getColumns() {
        return reflect.getFields().stream().map(this::mapField).collect(Collectors.toSet());
    }

    public Object[] getValues(T object) {
        return reflect.getFields().stream().map(param -> reflect.get(object, param)).toArray();
    }

    protected Object mapType(Object object) {
        if (object instanceof Timestamp) return ((Timestamp) object).toLocalDateTime();
        return object;
    }

    protected String mapField(String string) {
        return string.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    @Override
    public T mapRow(ResultSet resultSet, int i) throws SQLException {
        T obj = reflect.newInstance();
        for (String param : reflect.getFields()) reflect.set(obj, param, mapType(resultSet.getObject(mapField(param))));
        return obj;
    }
}