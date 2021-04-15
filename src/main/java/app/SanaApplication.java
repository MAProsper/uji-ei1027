package app;

import app.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SanaApplication implements CommandLineRunner {
    @Autowired TestDao testDao;

    public static void main(String[] args) {
        new SpringApplicationBuilder(SanaApplication.class).run(args);
    }

    public void run(String... strings) {
        testDao.run();
    }
}