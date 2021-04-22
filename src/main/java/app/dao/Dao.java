package app.dao;

import com.jayway.jsonpath.spi.mapper.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Dao<T> {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    Logger logger;

    //protected String table = getClass().getName().substring(getClass().getName().lastIndexOf('.')+1, getClass().getName().length()-3);
    // Lo de abajo es lo mismo que lo de arriba, pero con expresiones regulares  (para conseguir el nombre de la tabla a partir del nombre de la clase)
    protected String table = getClass().getName().replaceFirst(".*?(\\w+)Dao$", "$1");
    protected Class<? extends RowMapper<T>> rowMapper;

    /** Para añadir en la tabla, se pasa un objeto del tipo adecuado, y en cada DAO se gestiona como añadirlo
     * @param object objeto cuyos atributos serán los datos que se pongan en la Base de Datos
     */
    public abstract void add(T object);

    /** Para actualizar en la tabla, se pasa un objeto del tipo adecuado con los nuevos valores, y en cada DAO se gestiona como actualizarlos
     * @param object objeto cuyos atributos serán los datos que se pongan en la Base de Datos; utilizará la clave del objeto para actualizarla en la BD.
     */
    public abstract void update(T object);

    /** Método de borrado por columnas
     * @param column El nombre de la columna mediante la cual se quiere borrar
     * @param value El valor por el cual se quiere borrar
     */
    public void deleteByColumn(String column, Object value){
        jdbcTemplate.update(String.format("DELETE FROM Person WHERE %s =?", column), value);
    }

    /** Método para obtener un listado de todas las filas de la tabla
     * @return Devuelve una lista de objetos tal que cada objeto es una fila de la tabla.
     */
    protected List<T> getAll(){
        try {
            return jdbcTemplate.query(String.format("SELECT * FROM %s", this.table), this.rowMapper.newInstance());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw new MappingException(e.getMessage());
        }
    }

    /** Método para obtener un elemento cualquiera de la tabla (por favor, no hagas inyección SQL, crea un método específico para ello)
     * @param column El nombre de la columna de la cual quieres obtener datos
     * @param value El valor por el cual quieres buscar
     * @return Un objeto con todos los datos resultados de la búsqueda o null
     */
    protected T getByColumn(String column, Object value){
        try {
            return jdbcTemplate.queryForObject(String.format("SELECT * FROM %s WHERE %s =?", this.table, column ), this.rowMapper.newInstance(), value);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (InstantiationException | IllegalAccessException  e ) {
            throw new MappingException(e.getMessage());
        }
    }

    // Método para hacer pruebas en el terminal.
    public void test(){
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}