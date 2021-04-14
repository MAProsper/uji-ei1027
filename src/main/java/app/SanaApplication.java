package app;

import app.dao.address.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.logging.Logger;

@SpringBootApplication
public class SanaApplication implements CommandLineRunner {
    private static final Logger log = Logger.getLogger(SanaApplication.class.getName());

    @Autowired
    AddressDao addressDao;

    public static void main(String[] args) {
        new SpringApplicationBuilder(SanaApplication.class).run(args);
    }

    public void run(String... strings) {
        testDao();
    }

    void testDao() {
        log.info("test/dao");
        log.info(addressDao.getAll().toString());
    }
}