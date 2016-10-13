package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.billing.Vat;
import nl.avansc1.facturatie.model.customers.Customer;
import nl.avansc1.facturatie.repository.CustomerDAO;
import nl.avansc1.facturatie.repository.VatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by Pascal van Hoof on 13-10-2016.
 */


@Controller
@RequestMapping("/vat")
public class VatController {

    /**
     * Overview page
     * @return template/customer/overview.html
     */
    @RequestMapping("")
    public String overview() {
        return "vat/overview";
    }

    @RequestMapping("/new")
    public String add() {
        return "vat/add";
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addHandler(int percentage) {
        try {
            Vat vat = new  Vat(percentage);
            VatDAO.save(vat);
        } catch (Exception ex) {
            return "saving error";
        }

        return "saved!";
    }

    @Autowired
    private VatDAO VatDAO;

}
