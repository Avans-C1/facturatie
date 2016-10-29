package nl.avansc1.facturatie;

import nl.avansc1.facturatie.controller.CustomerController;
import org.mockito.Mockito;
import org.springframework.context.annotation.Configuration;

/**
 * {message}
 *
 * @author Bob van der Valk
 */
@Configuration
public class AppConfig {
    public CustomerController customerController() {
        return Mockito.mock(CustomerController.class);
    }
}
