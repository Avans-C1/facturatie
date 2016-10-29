package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.customers.Customer;
import nl.avansc1.facturatie.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
     * @return template/customer/index.html
     */
    @RequestMapping("")
    public String overview(Model model) {
        Iterable<Customer> customers = customerDAO.findAll();
        model.addAttribute("customers", customers);
        return "customer/index";
    }

    /**
     * Add new customers page
     * @return template/customer/add.html
     */
    @RequestMapping("/new")
    public String add(Model model) {
        return "customer/edit";
    }

    /**
     * Add new customers page
     * @return template/customer/add.html
     */
    @RequestMapping(value = "/show/{csn}")
    public String show(Model model, @PathVariable int csn) {
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customerDAO.findByCsn(csn));

        model.addAttribute("customers", customers);

        return "customer/index";
    }

    /**
     * Process the saving stuff
     * @return saved message is the customer is saved
     */
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(int csn, String firstName, String lastName, Date dateOfBirth, String streetName, String houseNumber,
                             String city, String zipcode, String phone, String email, String iban, Model model) {
        try {
            Customer customer = new Customer(csn, firstName, lastName, streetName, houseNumber, zipcode, city, dateOfBirth,
                    phone, email, iban);
            customerDAO.save(customer);
        } catch (Exception ex) {
            return editCustomer(model, csn);
        }

        model.addAttribute("success", "Customer saved!");

        return this.overview(model);
    }

    @RequestMapping(value = "/edit/{csn}", method = RequestMethod.GET)
    public String editCustomer(Model model, @PathVariable int csn) {
        Customer customer = customerDAO.findByCsn(csn);
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @RequestMapping(value = "/delete/{csn}")
    public String deleteCustomer(Model model, @PathVariable int csn) {
        Customer customer = customerDAO.findByCsn(csn);
        customerDAO.delete(customer);

        model.addAttribute("success", "Customer successfully deleted!");

        return this.overview(model);
    }

    @Autowired
    private CustomerDAO customerDAO;
}
