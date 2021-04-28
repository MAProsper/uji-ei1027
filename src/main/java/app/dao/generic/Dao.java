package app.dao.generic;

import app.model.generic.Model;
import app.util.Parametrized;
import app.util.Reflect;
import app.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class Dao<T extends Model> extends Parametrized<T> {
    @Autowired protected JdbcTemplate jdbc;
    @Autowired protected Logger logger;
    protected Mapper<T> mapper;

    public Dao() {
        mapper = new Mapper<>(new Reflect<>(getParametrizedType()));
    }

    /**
     * Añadir el objeto a la base de datos
     *
     * @param object objeto referencia
     */
    public void add(T object) {
        executeUpdate("INSERT INTO %s VALUES(%s)", "?", ", ", object);
    }

    /**
     * Actualiza el objeto de la base de datos
     *
     * @param object objeto referencia
     */
    public void update(T object) {
        executeUpdate("UPDATE %s SET %s", "%s = ?", ", ", object);
    }

    /**
     * Elimina el objeto de la base de datos
     *
     * @param object objeto referencia
     */
    public void delete(T object) {
        executeUpdate("DELETE FROM %s WHERE %s", "%s = ?", " AND ", object);
    }

    /**
     * Elimina el objeto de la base de datos
     *
     * @param id clave primaria
     */
    public void deleteById(int id) {
        executeUpdate("DELETE FROM %s WHERE id = ?", id);
    }

    /**
     * Obtener un objeto de la base de datos
     *
     * @param id clave primaria
     * @return objeto encontrado
     */
    public T getById(int id) {
        List<T> objets = executeQuery("WHERE id = ?", id);
        return objets.isEmpty() ? null : objets.get(0);
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
     * Ejecutar sentencia de actualizado SQL a partir de los datos de un objeto
     *
     * @param query     formato sentencia SQL
     * @param format    formato aplicado a cada atributo
     * @param delimiter delimitador entre atributos
     * @param object    objeto referencia
     */
    protected void executeUpdate(String query, String format, String delimiter, T object) {
        executeUpdate(String.format(query, "%s", SqlUtil.format(format, delimiter, mapper.getColumnNames())), mapper.toRow(object));
    }

    /**
     * Ejecutar sentencia de actualizado SQL a partir de los datos de un objeto
     *
     * @param query  formato sentencia SQL
     * @param values valores incrustados
     */
    protected void executeUpdate(String query, Object... values) {
        jdbc.update(String.format(query, mapper.getTableName()), values);
    }

    /**
     * Obtener todos los objetos de la sentencia SQL
     *
     * @param query  sentencia SQL
     * @param values valores incrustados
     * @return objetos encontrados
     */
    protected List<T> executeQuery(String query, Object... values) {
        try {
            return jdbc.query(String.format("SELECT * FROM %s AS T %s", mapper.getTableName(), query), mapper, values);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Tests ejecutados durante la comprobacion de DAOs
     */
    public void test() {
        logger.info(String.format("%s.getAll() = %s", getClass().getName(), getAll()));
    }

    public Mapper<T> getMapper() {
        return mapper;
    }
}