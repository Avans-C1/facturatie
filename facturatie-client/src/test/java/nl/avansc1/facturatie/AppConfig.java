package nl.avansc1.facturatie;

import nl.avansc1.facturatie.controller.CustomerController;
import nl.avansc1.facturatie.controller.InsuranceCompanyController;
import nl.avansc1.facturatie.controller.InsuranceController;
import nl.avansc1.facturatie.controller.PolicyController;
import nl.avansc1.facturatie.repository.InsuranceCompanyDAO;
import nl.avansc1.facturatie.repository.InsuranceDAO;
import nl.avansc1.facturatie.repository.PolicyDAO;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
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


    public InsuranceDAO insuranceDAO() {
        return Mockito.mock(InsuranceDAO.class);
    }


    public InsuranceCompanyDAO insuranceCompanyDAO() {
        return Mockito.mock(InsuranceCompanyDAO.class);
    }


    public PolicyDAO policyDAO() {
        return Mockito.mock(PolicyDAO.class);
    }


}
