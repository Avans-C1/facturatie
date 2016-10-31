package nl.avansc1.facturatie;

import nl.avansc1.facturatie.model.administration.InsuranceCompany;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Matthijs on 30-10-2016.
 * @author Matthijs Wilhelmus
 * @version 1.0
 *
 * This is a unit test for class <code>InsuranceCompanyController</code>
 * @see nl.avansc1.facturatie.controller.InsuranceCompanyController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, TestContextConfig.class})
public class InsuranceCompanyControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(InsuranceCompanyControllerTest.class);
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() { mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); logger.info("---- setUp ----");}

    @After
    public void tearDown() { logger.info("---- tearDown ----");

    }

    @Test
    public void PostEmptyForm() throws Exception {

        logger.info("---- PostEmptyForm ----");

        mockMvc.perform(post("/insurance_company/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("insuranceCompany", new InsuranceCompany())
        )
                // Ondanks dat de VALIDATIE failt kan de HTML pagina wel gevonden worden = HTTP 200
                //.andExpect(status().isOk())
                .andExpect(view().name("/insurance_company/edit"));
                // Attributen hieronder zijn de attributen van de Member class.
                //.andExpect(model().attributeHasFieldErrors("member", "firstName"))
                //.andExpect(model().attributeHasFieldErrors("member", "lastName"));
        //.andExpect(model().attribute("member", hasProperty("firstName", isEmptyOrNullString())))
        //.andExpect(model().attribute("member", hasProperty("emailAddress", isEmptyOrNullString())));
    }

    @Test
    public void SuccessfullyCreateInsuranceCompany() throws Exception {

        mockMvc.perform(post("/insurance_company/edit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //
                // Deze params zijn de case sensitive attributen van de (in dit geval) Member class.
                //
                .param("companyname", TestContextConfig.INSURANCE_COMPANY_NAME)
                .param("streetname", TestContextConfig.INSURANCE_COMPANY_STREETNAME)
                .param("houseNumber", TestContextConfig.INSURANCE_COMPANY_HOUSENUMBER)
                .param("zipcode", TestContextConfig.INSURANCE_COMPANY_ZIPCODE)
                .param("city", TestContextConfig.INSURANCE_COMPANY_CITY)
                .param("phoneNumber", TestContextConfig.INSURANCE_COMPANY_PHONENUMBER)
                .param("kvkNumber", TestContextConfig.INSURANCE_COMPANY_KVKNUMBER)
                .param("vat", TestContextConfig.INSURANCE_COMPANY_VAT_ID)
                .param("btw", TestContextConfig.INSURANCE_COMPANY_BTWNUMBER)
                .param("iban", TestContextConfig.INSURANCE_COMPANY_IBAN)
                .sessionAttr("insuranceCompany", new InsuranceCompany())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors());
    }

    @Test
    public void checkInsuranceCompanyNotExists() throws Exception {
        mockMvc.perform(get("/insurance_company/edit/1/")).andExpect(status().isNotFound());
    }

    @Test
    public void checkInsuranceCompanyHomepageExists() throws Exception {
        mockMvc.perform(get("/insurance_company/edit/")).andExpect(status().isFound());
    }
}
