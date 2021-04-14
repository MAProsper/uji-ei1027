import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.logging.Logger;

@SpringBootApplication
public class SanaApplication implements CommandLineRunner {
    private static final Logger log = Logger.getLogger(SanaApplication.class.getName());

    public static void main(String[] args) {
        new SpringApplicationBuilder(SanaApplication.class).run(args);
    }

    public void run(String... strings) {
    }
}
