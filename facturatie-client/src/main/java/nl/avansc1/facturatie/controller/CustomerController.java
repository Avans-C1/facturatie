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
    /**
     * Overview page
     * @return template/customer/overview.html
     */
    @RequestMapping("")
    public String overview() {
        return "customer/overview";
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
     * @return saved message is the customer is saved
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addHandler(int csn, String firstName, String lastName, String streetName, String houseNumber,
                             String zipcode, String city, Date dateOfBirth, int phoneNumber, String email,
                             String iban) {
        try {
            Customer customer = new Customer(csn, firstName, lastName, streetName, houseNumber, zipcode, city, dateOfBirth,
                    phoneNumber, email, iban);
            customerDAO.save(customer);
        } catch (Exception ex) {
            return "saving error";
        }

        return "saved!";
    }

    @Autowired
    private CustomerDAO customerDAO;
}
