package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.customers.Customer;
import nl.avansc1.facturatie.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * This controls the customer crud and front-end
 *
 * @author Bob van der Valk
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @RequestMapping("/")
    public String overview() {
        return "howdy";
    }

    /**
     * Add new customers page
     * @return template/customer/add.html
     */
    @RequestMapping("/new")
    public String add() {
        return "customer/add";
    }

    /**
     * Process the saving stuff
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addHandler(int csn, String firstName, String lastName, String streetName, String houseNumber,
                             String zipcode, String city, Date dateOfBirth, int phoneNumber, String email,
                             String iban) {
        Customer customer = new Customer();
        customer.setCsn(csn);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setStreetName(streetName);
        customer.setHouseNumber(houseNumber);
        customer.setZipcode(zipcode);
        customer.setCity(city);
        customer.setDateOfBirth(dateOfBirth);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        customer.setIban(iban);
        customerDAO.save(customer);
        return "saved!";
    }

    @Autowired
    private CustomerDAO customerDAO;
}
