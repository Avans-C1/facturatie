package nl.avansc1.facturatie.library.controller;

import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import nl.avansc1.facturatie.config.TestContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("Duplicates")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration
public class AuthControllerTest {

    @Autowired
    WebApplicationContext context;

    private WebClient webClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(context)
                .contextPath("")
                .build();
    }

    @After
    public void tearDown() {
        this.webClient.close();
    }

    @Test
    public void test_login_page_loads() throws IOException {
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        TextPage loginPage = webClient.getPage("http://localhost:8080/login");

        // verify we successfully created a message and displayed the newly create message
        assertThat(loginPage.getUrl().toString()).endsWith("/login");
        System.out.println(loginPage.getContent());
//        assertThat(loginPage.getTitleText()).isEqualTo("Avans Bieb");
    }
}
