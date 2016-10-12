package nl.avansc1.facturatie.api.controller;

import nl.avansc1.facturatie.api.model.customers.Customer;
import nl.avansc1.facturatie.api.model.customers.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * This class is a controller that manages the Customer
 *
 * @author Bob van der Valk
 */
@Controller
public class CustomerController {

    @RequestMapping("get")
    @ResponseBody
    public String getByCNC(int csn) {
        StringWriter writer;
        try {
            writer = new StringWriter();
            Customer customer = customerDao.findByCsn(csn);
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(customer, writer);
        } catch (Exception ex) {
            return "Customer not found";
        }
        return writer.toString();
    }

    @Autowired
    private CustomerDao customerDao;
}
