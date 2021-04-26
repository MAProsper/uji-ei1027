package app.dao.generic;

import app.util.Reflect;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import java.sql.*;
import java.util.Set;
import java.util.stream.Collectors;

public class Mapper<T> implements RowMapper<T> {
    protected Reflect<T> reflect;

    public Mapper(Class<T> cls) {
        reflect = new Reflect<>(cls);
    }

    /**
     * Nombre de la tabla SQL a partir del modelo
     *
     * @return nombre de tabla
     */
    public String getTableName() {
        return reflect.getReflectClass().getSimpleName();
    }

    /**
     * Nombre de las columnas SQL a partir de los atributos del modelo
     *
     * @return nombre de las columnas
     */
    public Set<String> getColumnNames() {
        return mapName(reflect.getFields());
    }

    /**
     * Traducción de un atributo del modelo a columna de SQL
     *
     * @param name nombre de atributo
     * @return nombre de columna
     */
    public String mapName(String name) {
        return name.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    /**
     * Traducción de varios atributo del modelo a columnas de SQL
     *
     * @param names nombres de atributos
     * @return nombres de columnas
     */
    public Set<String> mapName(Set<String> names) {
        return names.stream().map(this::mapName).collect(Collectors.toSet());
    }

    /**
     * Traducción de objetos SQL a objetos del modelo
     *
     * @param object objeto SQL
     * @return objeto para el modelo
     */
    public Object mapType(Object object) {
        if (object instanceof Timestamp) return ((Timestamp) object).toLocalDateTime();
        if (object instanceof Date) return ((Date) object).toLocalDate();
        if (object instanceof Time) return ((Time) object).toLocalTime();
        return object;
    }

    /**
     * Traducción de fila SQL a un objeto del modelo
     *
     * @param resultSet fila SQL
     * @param i         numero de fila en la respuesta SQL
     * @return objeto modelo
     * @throws SQLException error de SQL
     */
    @Override
    public T mapRow(@NonNull ResultSet resultSet, int i) throws SQLException {
        T obj = reflect.newInstance();
        for (String field : reflect.getFields())
            reflect.set(obj, field, mapType(resultSet.getObject(mapName(field))));
        return obj;
    }

    /**
     * Traducción de un objeto del modelo a fila de valores
     *
     * @param object objeto referencia
     * @return fila de valores
     */
    public Object[] toRow(T object) {
        return toRow(object, reflect.getFields());
    }

    /**
     * Traducción de parte un objeto del modelo a fila de valores
     *
     * @param object objeto referencia
     * @param fields atributos a utilizar
     * @return fila de valores
     */
    public Object[] toRow(T object, Set<String> fields) {
        return fields.stream().map(field -> reflect.get(object, field)).toArray();
    }
}