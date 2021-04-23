package app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Dao<T> {
    @Autowired protected JdbcTemplate jdbc;
    @Autowired protected Logger logger;
    protected Mapper<T> mapper;

    public Dao(Class<T> cls) {
        this(new Mapper<>(cls));
    }

    public Dao(Mapper<T> mapper) {
        this.mapper = mapper;
    }

    /**
     * Añadir el objeto a la base de datos
     *
     * @param object objeto referencia
     */
    public void add(T object) {
        executeUpdate("INSERT INTO %s VALUES(%s)", "%s =?", ", ", object);
    }

    /**
     * Actualiza el objeto de la base de datos
     *
     * @param object objeto referencia
     */
    public void update(T object) {
        executeUpdate("UPDATE %s SET %s", "?", ", ", object);
    }

    /**
     * Elimina el objeto de la base de datos
     *
     * @param object objeto referencia
     */
    public void delete(T object) {
        executeUpdate("DELETE FROM %s WHERE %s", "%s =?", " AND ", object);
    }

    /**
     * Obtener todos los objetos de la tabla en la base de datos
     *
     * @return objetos encontrados
     */
    public List<T> getAll() {
        return executeQuery(Collections.emptyMap());
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
        jdbc.update(String.format(query, mapper.getTableName(), format(format, delimiter, mapper.getColumnNames())), mapper.toRow(object));
    }

    /**
     * Obtener todos los objetos de la tabla que cumplan todos los atributos espedificados
     *
     * @param fields atributos de busqueda
     * @return objetos encontrados
     */
    protected List<T> executeQuery(Map<String, Object> fields) {
        String query = format("%s =?", " AND ", fields.keySet());
        try {
            return jdbc.query(String.format("SELECT * FROM %s WHERE %s", mapper.getTableName(), query), mapper, fields.values().toArray());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Prepara una cadena SQL dando un formato determinado a cada atributo y concatenado los con un delimitador
     *
     * @param format    formato aplicado a cada atributo
     * @param delimiter delimitador entre atributos
     * @param fields    atributos a ultilizar
     * @return cadena SQL
     */
    protected String format(String format, String delimiter, Set<String> fields) {
        String sql = mapper.mapField(fields).stream().map(field -> String.format(format, field)).collect(Collectors.joining(delimiter));
        return sql.isBlank() ? "TRUE" : sql;
    }

    /**
     * Tests ejecutados durante a comprobacion de DAOs
     */
    public void test() {
        logger.info(String.format("%s.getAll() = %s", getClass().getName(), getAll()));
    }
}