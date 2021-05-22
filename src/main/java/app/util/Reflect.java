package app.util;

import app.ApplicationException;
import app.model.generic.Model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reflect<T extends Model> {
    protected Class<T> cls;
    protected Map<String, Field> fields;

    public Reflect(Class<T> cls) {
        this.cls = cls;
        fields = new TreeMap<>();
        for (Field field : cls.getFields())
            fields.put(field.getName(), field);
    }

    /**
     * Obtiene la clase de los objetos reflejados
     *
     * @return objeto clase
     */
    public Class<T> getReflectClass() {
        return cls;
    }

    /**
     * Crea una nueva instancia del objeto reflejado
     *
     * @return instancia de objeto
     */
    public T newInstance() {
        try {
            return cls.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }

    /**
     * Obtener una lista con todos los atributos del objeto
     *
     * @return lista de atributos
     */
    public Set<String> getFields() {
        return fields.keySet();
    }

    /**
     * Establecer el atributo name del objeto a value
     *
     * @param object objeto referencia
     * @param name   nombre de atributo
     * @param value  valor del atributo
     */
    public void set(T object, String name, Object value) {
        try {
            fields.get(name).set(object, value);
        } catch (IllegalAccessException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }

    /**
     * Obtener el valor del atributo name del objeto
     *
     * @param object objeto referencia
     * @param name   nombre del atributo
     * @return valor del atributo
     */
    public Object get(T object, String name) {
        try {
            return fields.get(name).get(object);
        } catch (IllegalAccessException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }

    public void merge(T src, T dst) {
        Set<String> fixed = dst.getFinal().stream().filter(field -> get(src, field) != null).collect(Collectors.toSet());
        Set<String> miss = getFields().stream().filter(field -> get(dst, field) == null).collect(Collectors.toSet());
        Stream.of(fixed, miss).flatMap(Set::stream).forEach(field -> set(dst, field, get(src, field)));
    }
}