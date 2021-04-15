package app;

import app.util.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SanaApplication implements CommandLineRunner {
    @Autowired Test test;

    public static void main(String[] args) {
        new SpringApplicationBuilder(SanaApplication.class).run(args);
    }

    public void run(String... strings) {
        test.run();
    }
}