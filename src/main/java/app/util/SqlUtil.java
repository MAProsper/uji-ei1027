package app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.UncategorizedDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class SqlUtil {
    @Autowired DataSource dataSource;

    public void executeScript(String name) throws DataAccessException {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new CannotGetJdbcConnectionException(e.getMessage());
        }

        Resource resource = new ClassPathResource(String.format("sql/%s.sql", name));
        ScriptUtils.executeSqlScript(connection, resource);
    }
}
