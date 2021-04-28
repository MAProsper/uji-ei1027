package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired Test test;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }

    public void run(String... strings) {
        test.run();
    }
}