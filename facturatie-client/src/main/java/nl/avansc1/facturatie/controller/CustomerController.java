package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.customers.Customer;
import nl.avansc1.facturatie.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * Add new customers page
     * @return template/customer/add.html
     */
    @RequestMapping(value = "/show/{csn}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable int csn) {
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customerDAO.findByCsn(csn));

        model.addAttribute("customers", customers);

        return "customer/overview";
    }

    /**
     * Process the saving stuff
     * @return saved message is the customer is saved
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
//    csn=1234567&firstName=Bob&lastName=van%20der%20Valk&dateofbirth=1993-10-27&streetname=straat&housenumber=8&city=Heinenoord&zipcode=3274NB&phone=651355588&email=bobvandervalk%40gmail.com&iban=NL00INGB0000000000
    public String addHandler(int csn, String firstName, String lastName, Date dateOfBirth, String streetName, String houseNumber,
                             String city, String zipcode, int phone, String email, String iban) {
        try {
            Customer customer = new Customer(csn, firstName, lastName, streetName, houseNumber, zipcode, city, dateOfBirth,
                    phone, email, iban);
            customerDAO.save(customer);
        } catch (Exception ex) {
            return "saving error";
        }

        return "saved!";
    }

    @Autowired
    private CustomerDAO customerDAO;
}
