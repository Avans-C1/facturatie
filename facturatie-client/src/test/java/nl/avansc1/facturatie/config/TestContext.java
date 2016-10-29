package nl.avansc1.facturatie.config;

import nl.avansc1.facturatie.repository.UserDAO;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Petri Kainulainen
 */
@Configuration
public class TestContext {

    public static final String MEMBER_FIRSTNAME = "Voornaam";
    public static final String MEMBER_LASTNAME = "Achternaam";
    public static final String MEMBER_STREET = "Straatnaam";
    public static final String MEMBER_HOUSENUMBER = "123";
    public static final String MEMBER_CITY = "Stadnaam";
    public static final String MEMBER_PHONENUMBER = "06-12345678";
    public static final String MEMBER_EMAILADDRESS_VALID = "test.user@server.com";
    public static final String MEMBER_EMAILADDRESS_INVALID = "_invalid_email_";

//    @Bean
//    public BookService bookService() {
//        return Mockito.mock(BookService.class);
//    }

    @Bean
    public UserDAO memberService() {
        return Mockito.mock(UserDAO.class);
    }
}
