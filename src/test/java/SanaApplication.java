import dao.address.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.logging.Logger;

@SpringBootApplication
public class SanaApplication implements CommandLineRunner {
    private JdbcTemplate jdbcTemplate;
    private static final Logger log = Logger.getLogger(SanaApplication.class.getName());

    @Autowired
    AddressDao addressDao;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(SanaApplication.class).run(args);
    }

    public void run(String... strings) {
        log.info(addressDao.getAll().toString());
    }
}