package app.util;

import org.springframework.dao.DataRetrievalFailureException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Reflect<T> {
    protected Class<T> cls;
    protected Map<String, Field> fields;

    public Reflect(Class<T> cls) {
        this.cls = cls;
        fields = new HashMap<>();
        for (Field field : cls.getDeclaredFields()) {
            fields.put(field.getName(), field);
        }
    }

    /**
     * @return clase de objectos los objetos reflejados
     */
    public Class<T> getReflectClass() {
        return cls;
    }

    /**
     * @return nueva instancia del objecto relefjado
     */
    public T newInstance() {
        try {
            return cls.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new DataRetrievalFailureException(e.getMessage(), e);
        }
    }

    /**
     * @return lista de atributos en la clase reflejada
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
            throw new DataRetrievalFailureException(e.getMessage(), e);
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
            throw new DataRetrievalFailureException(e.getMessage(), e);
        }
    }


}
