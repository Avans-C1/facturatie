package nl.avansc1.facturatie;

import nl.avansc1.facturatie.model.billing.Invoice;
import nl.avansc1.facturatie.repository.InvoiceDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;


/**
 * Created by kevin on 31-10-2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class InvoiceTest {

    @Autowired
    private InvoiceDAO invoiceDAO;
    /*
     @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
     */

    /*@Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

     @Test
    public void testListInvoices() throws Exception {
        mvc.perform(get("/invoice/")
                .with(user("admin@test.com").password("password").roles("ADMIN"))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("invoice/index"))
                .andExpect(forwardedUrl("/resources/invoice/index.html"))
        ;
    }
    */

    @Test
    public void findAllInRepository() {
        List<Invoice> searchResults = (List<Invoice>) invoiceDAO.findAll();
        assertThat(searchResults, hasSize(2));
    }


}
