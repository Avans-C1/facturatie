package nl.avansc1.facturatie.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * This is the main class that runs the API server
 *
 * @author Bob van der Valk
 */
@SpringBootApplication
public class API extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(API.class, args);
    }
}
