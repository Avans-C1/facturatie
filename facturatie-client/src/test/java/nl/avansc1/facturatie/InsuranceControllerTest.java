package nl.avansc1.facturatie;


import nl.avansc1.facturatie.model.insurances.Insurance;
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
 * This is a unit test for class <code>InsuranceController</code>
 * @see nl.avansc1.facturatie.controller.InsuranceController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, TestContextConfig.class})
public class InsuranceControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(InsuranceControllerTest.class);
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() { mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); }

    @After
    public void tearDown() { logger.info("---- tearDown ----");

    }

    @Test
    public void PostEmptyForm() throws Exception {

        logger.info("---- PostEmptyForm ----");

        mockMvc.perform(post("/insurance/create/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("insurance", new Insurance())
        )
                // Ondanks dat de VALIDATIE failt kan de HTML pagina wel gevonden worden = HTTP 200
                //.andExpect(status().isOk())
                .andExpect(view().name("/insurance/index/"));
        // Attributen hieronder zijn de attributen van de Member class.
        //.andExpect(model().attributeHasFieldErrors("member", "firstName"))
        //.andExpect(model().attributeHasFieldErrors("member", "lastName"));
        //.andExpect(model().attribute("member", hasProperty("firstName", isEmptyOrNullString())))
        //.andExpect(model().attribute("member", hasProperty("emailAddress", isEmptyOrNullString())));
    }

    @Test
    public void SuccessfullyCreateInsurance() throws Exception {

        mockMvc.perform(post("/insurance/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //
                // Deze params zijn de case sensitive attributen van de (in dit geval) Member class.
                //
                .param("insuranceCompany", TestContextConfig.INSURANCE_COMPANY_ID)
                .param("name", TestContextConfig.INSURANCE_NAME)
                .param("monthlyFee", TestContextConfig.INSURANCE_MONTHLYFEE)
                .param("coveredTreatments", TestContextConfig.INSURANCE_COVEREDTREATMENTS)
                .sessionAttr("insurance", new Insurance())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors());
    }

    @Test
    public void checkInsuranceNotExists() throws Exception {
        mockMvc.perform(get("/insurance/show/1/")).andExpect(status().isNotFound());
    }

    @Test
    public void checkInsuranceHomepageExists() throws Exception {
        mockMvc.perform(get("/insurance/index/")).andExpect(status().isFound());
    }
}
