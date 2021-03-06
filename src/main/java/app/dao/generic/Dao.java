package app.dao.generic;

import app.ApplicationException;
import app.model.generic.Model;
import app.util.Parametrized;
import app.util.Reflect;
import app.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public abstract class Dao<T extends Model> extends Parametrized<T> {
    protected Mapper<T> mapper = new Mapper<>(getParametrizedType());
    @Autowired protected JdbcTemplate jdbc;
    @Autowired protected Logger logger;

    /**
     * Añadir el objeto a la base de datos
     *
     * @param object objeto referencia
     */
    public void add(T object) {
        object.setId(getNextId());
        Set<String> columns = mapper.getColumnNames();
        String keys = StringUtil.formatJoin("%s", columns);
        String values = StringUtil.formatJoin("?", columns);
        executeUpdate(String.format("INSERT INTO %%s (%s) VALUES (%s)", keys, values), mapper.toRow(object));
    }

    /**
     * Actualiza el objeto de la base de datos
     *
     * @param object objeto referencia
     */
    public void update(T object) {
        getReflect().merge(getById(object.getId()), object);
        Object[] values = mapper.toRow(object);
        values = Arrays.copyOf(values, values.length + 1);
        values[values.length - 1] = object.getId();
        String args = StringUtil.formatJoin("%s = ?", mapper.getColumnNames());
        executeUpdate(String.format("UPDATE %%s SET %s WHERE id = ?", args), values);
    }

    /**
     * Elimina el objeto de la base de datos
     *
     * @param id clave primaria
     */
    public void delete(int id) {
        executeUpdate("DELETE FROM %s WHERE id = ?", id);
    }

    /**
     * Obtener un objeto de la base de datos
     *
     * @param id clave primaria
     * @return objeto encontrado
     */
    public T getById(int id) {
        return getFirst(getByField("id", id));
    }

    /**
     * Obtener todos los objetos de la base de datos
     *
     * @return objetos encontrados
     */
    public List<T> getAll() {
        return executeQuery("");
    }

    /**
     * Obtener el objeto que contiene a otro
     * Municipality = getParentOf(Area)
     *
     * @param other objeto contenido
     * @return objeto contenedor
     */
    @SuppressWarnings("unchecked")
    public T getParentOf(Model other) {
        Mapper<Model> mapper = new Mapper<>((Class<Model>) other.getClass());
        return getById((Integer) mapper.getReflect().get(other, StringUtil.toPackageCase(this.mapper.getTableName())));
    }

    /**
     * Objeto los objetos contenidos por otro
     *
     * @param other objeto contenedor
     * @return objetos contenidos
     */
    public List<T> getChildsOf(Model other) {
        Mapper<?> mapper = new Mapper<>(other.getClass());
        return getByField(mapper.getTableName(), other.getId());
    }

    /**
     * Obtiene todos los objetos que tengan un campo concreto
     *
     * @param field nombre del atributo
     * @param value valor del atributo
     * @return objetos relacionados
     */
    protected List<T> getByField(String field, Object value) {
        return executeQuery(String.format("WHERE %s = ?", mapper.mapName(field)), value);
    }

    /**
     * Ejecutar sentencia de actualizado SQL a partir de los datos de un objeto
     *
     * @param query  formato sentencia SQL
     * @param values valores incrustados
     */
    protected void executeUpdate(String query, Object... values) {
        query = String.format(query, mapper.getTableName());
        try {
            jdbc.update(query, values);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationException("Error de integridad", e);
        }
    }

    /**
     * Obtener todos los objetos de la sentencia SQL
     *
     * @param query  sentencia SQL
     * @param values valores incrustados
     * @return objetos encontrados
     */
    protected List<T> executeQuery(String query, Object... values) {
        query = String.format("SELECT * FROM %s AS T %s ORDER BY -id", mapper.getTableName(), query);
        try {
            return jdbc.query(query, mapper, values);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Obtiene el siguiente id disponible
     *
     * @return id disponible
     */
    protected int getNextId() {
        String query = String.format("SELECT MAX(id) FROM %s", mapper.getTableName());
        Integer id = jdbc.queryForObject(query, Integer.class);
        return id == null ? 1 : ++id;
    }

    /**
     * Tests ejecutados durante la comprobacion de DAOs
     */
    public void test() {
        logger.info(String.format("%s.getAll() = %s", getClass().getName(), getAll()));
    }

    protected T getFirst(List<T> objects) {
        return objects.isEmpty() ? null : objects.get(0);
    }

    public Mapper<T> getMapper() {
        return mapper;
    }

    public Reflect<T> getReflect() {
        return mapper.getReflect();
    }
}