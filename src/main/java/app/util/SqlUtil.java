package app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class SqlUtil {
    @Autowired DataSource dataSource;

    /**
     * Ejecuta un script SQL
     *
     * @param name nombre del script
     */
    public void executeScript(String name) {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new DataRetrievalFailureException(e.getMessage(), e);
        }

        Resource resource = new ClassPathResource(String.format("sql/%s.sql", name));
        ScriptUtils.executeSqlScript(connection, resource);
    }
}
