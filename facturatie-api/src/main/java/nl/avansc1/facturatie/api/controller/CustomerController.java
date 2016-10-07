package nl.avansc1.facturatie.api.controller;

import nl.avansc1.facturatie.api.model.customers.Customer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a controller that manages the Customer
 *
 * @author Bob van der Valk
 */
@RestController
public class CustomerController {

    @RequestMapping("/customer")
    public Customer customer(@RequestParam(value = "cnc") int cnc){

        return new Customer();
    }
}
