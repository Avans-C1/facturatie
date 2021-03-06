package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.administration.User;
import nl.avansc1.facturatie.model.customers.Customer;
import nl.avansc1.facturatie.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @ModelAttribute("page")
    public String module() {
        return "customers";
    }

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
    @PostMapping(value = "/store")
    String store(Model model, @ModelAttribute Customer customer) {
        customerDAO.save(customer);
        model.addAttribute("success", "Customer successfully saved");

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
