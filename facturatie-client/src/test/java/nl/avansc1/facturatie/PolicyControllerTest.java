package nl.avansc1.facturatie;

import nl.avansc1.facturatie.model.insurances.Policy;
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
 * This is a unit test for class <code>PolicyController</code>
 * @see nl.avansc1.facturatie.controller.PolicyController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, TestContextConfig.class})
public class PolicyControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(PolicyControllerTest.class);
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

        mockMvc.perform(post("/policy/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("policy", new Policy())
        )
                // Ondanks dat de VALIDATIE failt kan de HTML pagina wel gevonden worden = HTTP 200
                //.andExpect(status().isOk())
                .andExpect(view().name("/policy/create"));
                // Attributen hieronder zijn de attributen van de Member class.
                //.andExpect(model().attributeHasFieldErrors("member", "firstName"))
                //.andExpect(model().attributeHasFieldErrors("member", "lastName"));
                //.andExpect(model().attribute("member", hasProperty("firstName", isEmptyOrNullString())))
                //.andExpect(model().attribute("member", hasProperty("emailAddress", isEmptyOrNullString())));
    }

    @Test
    public void SuccessfullyCreatePolicy() throws Exception {

        mockMvc.perform(post("/policy/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //
                // Deze params zijn de case sensitive attributen van de (in dit geval) Member class.
                //
                .param("customer", TestContextConfig.POLICY_CUSTOMER_ID)
                .param("insurance", TestContextConfig.POLICY_INSURANCE_ID)
                .param("contribution", TestContextConfig.POLICY_CONTRIBUTION)
                .param("dateStart", TestContextConfig.POLICY_DATESTART)
                .param("dateEnd", TestContextConfig.POLICY_DATEEND)
                .param("active", TestContextConfig.POLICY_ACTIVE)
                .param("contributionUsed", TestContextConfig.POLICY_CONTRIBUTIONUSED)
                .sessionAttr("policy", new Policy())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors());
    }

    @Test
    public void checkPolicyNotExists() throws Exception {
        mockMvc.perform(get("/policy/edit/1/")).andExpect(status().isNotFound());
    }

    @Test
    public void checkInsuranceHomepageExists() throws Exception {
        mockMvc.perform(get("/policy/index")).andExpect(status().isFound());
    }
}
