package app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Dao<T> {
    @Autowired protected JdbcTemplate jdbc;
    @Autowired protected Logger logger;
    protected Mapper<T> mapper;
    protected String table;

    public Dao(Class<T> cls) {
        table = cls.getSimpleName();
        mapper = new Mapper<>(cls);
    }

    //protected String table = getClass().getName().substring(getClass().getName().lastIndexOf('.')+1, getClass().getName().length()-3);
    // Lo de abajo es lo mismo que lo de arriba, pero con expresiones regulares  (para conseguir el nombre de la tabla a partir del nombre de la clase)
    protected String name = getClass().getSimpleName().replaceFirst("Dao$", "");
    protected String model = getClass().getName().replaceFirst("\\.dao\\.", ".model.").replaceFirst("Dao$", "");

    /**
     * Para añadir en la tabla, se pasa un objeto del tipo adecuado, y en cada DAO se gestiona como añadirlo
     *
     * @param object objeto cuyos atributos serán los datos que se pongan en la Base de Datos
     */
    public void add(T object) {
        String names = mapper.getColumns().stream().map(param -> "?").collect(Collectors.joining(", "));
        jdbc.update(String.format("INSERT INTO %s VALUES(%s)", name, names), mapper.getValues(object));
    }

    /**
     * Para actualizar en la tabla, se pasa un objeto del tipo adecuado con los nuevos valores, y en cada DAO se gestiona como actualizarlos
     *
     * @param object objeto cuyos atributos serán los datos que se pongan en la Base de Datos; utilizará la clave del objeto para actualizarla en la BD.
     */
    public void update(T object) {
        String names = mapper.getColumns().stream().map(param -> "%s =?").collect(Collectors.joining(", "));
        jdbc.update(String.format("UPDATE %s SET %s", name, names), mapper.getValues(object));
    }

    /**
     * Método de borrado por columnas
     *
     * @param param El nombre de la columna mediante la cual se quiere borrar
     * @param value El valor por el cual se quiere borrar
     */
    protected void deleteByParameter(String param, Object value) {
        jdbc.update(String.format("DELETE FROM Person WHERE %s =?", param), value);
    }

    /**
     * Método para obtener un listado de todas las filas de la tabla
     *
     * @return Devuelve una lista de objetos tal que cada objeto es una fila de la tabla.
     */
    public List<T> getAll() {
        try {
            return jdbc.query(String.format("SELECT * FROM %s", this.name), mapper);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Método para obtener un elemento cualquiera de la tabla (por favor, no hagas inyección SQL, crea un método específico para ello)
     *
     * @param param El nombre de la columna de la cual quieres obtener datos
     * @param value El valor por el cual quieres buscar
     * @return Un objeto con todos los datos resultados de la búsqueda o null
     */
    protected T getByParameter(String param, Object value) {
        try {
            return jdbc.queryForObject(String.format("SELECT * FROM %s WHERE %s =?", this.name, param), mapper, value);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Método para hacer pruebas en el terminal.
    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}