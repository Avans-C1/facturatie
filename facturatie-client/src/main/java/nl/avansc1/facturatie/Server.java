package nl.avansc1.facturatie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is used to run the Spring Boot application. It will start a tomcat server to load all the template files.

 * You can find the server on default: http://localhost:8080.
 * 
 * @author Bob van der Valk
 */
@SpringBootApplication
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
