package nl.avansc1.facturatie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * This class is used to run the Spring Boot application. It will start a tomcat server to load all the template files.

 * You can find the server on default: http://localhost:8080.
 * TODO: Still show a error in the webbrowser because we didn't add a template file yet.
 * 
 * @author Bob van der Valk
 */
@SpringBootApplication
public class Server extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
