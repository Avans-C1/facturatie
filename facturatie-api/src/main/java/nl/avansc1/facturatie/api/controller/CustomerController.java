package nl.avansc1.facturatie.api.controller;

import nl.avansc1.facturatie.api.model.customers.Customer;
import nl.avansc1.facturatie.api.model.customers.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class is a controller that manages the Customer
 *
 * @author Bob van der Valk
 */
@Controller
public class CustomerController {

    @RequestMapping("get")
    @ResponseBody
    public String getByCNC(int cnc) {
        String lastName;

        try {
            Customer customer = customerDao.findByCnc(cnc);
            lastName = customer.getLastName();
        } catch (Exception ex) {
            return "Client not found";
        }
        return "The last name is "+ lastName;
    }

    @Autowired
    private CustomerDao customerDao;
}
