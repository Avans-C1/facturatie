package nl.avansc1.facturatie;

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
 * Created by Matthijs on 30-10-2016.
 */
@Configuration
public class TestContextConfig {

    public static final String POLICY_CUSTOMER_ID = "1";
    public static final String POLICY_INSURANCE_ID = "1";
    public static final String POLICY_CONTRIBUTION = "385";
    public static final String POLICY_CONTRIBUTION_INVALID = "384";
    public static final String POLICY_DATESTART = " ";
    public static final String POLICY_DATEEND = " ";
    public static final String POLICY_ACTIVE = "1";
    public static final String POLICY_CONTRIBUTIONUSED = "0";

    public static final String INSURANCE_COMPANY_ID = "1";
    public static final String INSURANCE_NAME = "BASIS";
    public static final String INSURANCE_MONTHLYFEE = "75.00";
    public static final String INSURANCE_MONTHLYFEE_INVALID = "69.00";
    public static final String INSURANCE_COVEREDTREATMENTS = "1";

    public static final String INSURANCE_COMPANY_NAME = "Voornaam";
    public static final String INSURANCE_COMPANY_STREETNAME = "Straatnaam";
    public static final String INSURANCE_COMPANY_HOUSENUMBER = "123";
    public static final String INSURANCE_COMPANY_ZIPCODE = "Postcode";
    public static final String INSURANCE_COMPANY_CITY = "Stadnaam";
    public static final String INSURANCE_COMPANY_PHONENUMBER = "0612345678";
    public static final String INSURANCE_COMPANY_EMAILADDRESS_VALID = "test.user@server.com";
    public static final String INSURANCE_COMPANY_EMAILADDRESS_INVALID = "_invalid_email_";
    public static final String INSURANCE_COMPANY_KVKNUMBER = "12345678";
    public static final String INSURANCE_COMPANY_VAT_ID = "0";
    public static final String INSURANCE_COMPANY_BTWNUMBER = "12345678";
    public static final String INSURANCE_COMPANY_IBAN = "12345678";

    @Bean
    public InsuranceDAO insuranceDAO() {
        return Mockito.mock(InsuranceDAO.class);
    }

    @Bean
    public InsuranceCompanyDAO insuranceCompanyDAO() {
        return Mockito.mock(InsuranceCompanyDAO.class);
    }

    @Bean
    public PolicyDAO policyDAO() {
        return Mockito.mock(PolicyDAO.class);
    }
}
