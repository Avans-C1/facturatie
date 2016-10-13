package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.administration.InsuranceCompany;
import nl.avansc1.facturatie.model.billing.Vat;
import nl.avansc1.facturatie.repository.InsuranceCompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Matthijs on 13-10-2016.
 */
@Controller
@RequestMapping("/insuranceCompany")
public class InsuranceCompanyController {
    @RequestMapping("")
    public String index(){
        return "insurance/index";
    }
    /**
     * Overview page
     * @return template/insurance/overview.html
     */

    @RequestMapping("/new")
    public String add() {
        return "insurance/add";
    }

    /**
     * Process the saving stuff
     * @return saved message is the insurance is saved
     */

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addHandler(int id, String name, String houseNumber, String zipcode, String city,
                             int phoneNumber, String email, int kvkNumber, Vat vat) {
        try {
            InsuranceCompany insurance = new InsuranceCompany(id, name, houseNumber, zipcode, city, phoneNumber, email, kvkNumber, vat);
            insuranceDAO.save(insurance);
        } catch (Exception ex) {
            return "saving error";
        }

        return "saved!";
    }

    @Autowired
    private InsuranceCompanyDAO insuranceDAO;
}
