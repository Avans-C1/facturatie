package nl.avansc1.facturatie;

import nl.avansc1.facturatie.controller.CustomerController;
import nl.avansc1.facturatie.controller.InsuranceCompanyController;
import nl.avansc1.facturatie.controller.InsuranceController;
import nl.avansc1.facturatie.controller.PolicyController;
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

    public InsuranceController insuranceController() {
        return Mockito.mock(InsuranceController.class);
    }

    public PolicyController policyController() {
        return  Mockito.mock(PolicyController.class);
    }

    public InsuranceCompanyController insuranceCompanyController() {
        return Mockito.mock(InsuranceCompanyController.class);
    }
}
