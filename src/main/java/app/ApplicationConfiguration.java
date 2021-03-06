package app;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.logging.Logger;

@org.springframework.context.annotation.Configuration
public class ApplicationConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "source")
    public DataSource source() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate jdbc() {
        return new JdbcTemplate(source());
    }

    @Bean
    public Logger logger() {
        return Logger.getLogger(Application.class.getName());
    }

    @Bean
    public PasswordEncryptor encryptor() {
        return new BasicPasswordEncryptor();
    }
}
