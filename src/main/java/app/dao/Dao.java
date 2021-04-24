package app.dao;

import app.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
        executeUpdate("INSERT INTO %s VALUES(%s)", "?", ", ", object);
    }

    /**
     * Actualiza el objeto de la base de datos
     *
     * @param object objeto referencia
     */
    public void update(T object) {
        executeUpdate("UPDATE %s SET %s", "%s =?", ", ", object);
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
        jdbc.update(String.format(query, mapper.getTableName(), SqlUtil.format(format, delimiter, mapper.getColumnNames())), mapper.toRow(object));
    }

    /**
     * Obtener todos los objetos de la tabla que cumplan todos los atributos espedificados
     *
     * @param fields atributos de busqueda
     * @return objetos encontrados
     */
    protected List<T> executeQuery(Map<String, Object> fields) {
        String query = SqlUtil.format("%s =?", " AND ", mapper.mapName(fields.keySet()));
        try {
            return jdbc.query(String.format("SELECT * FROM %s WHERE %s", mapper.getTableName(), query), mapper, fields.values().toArray());
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
}