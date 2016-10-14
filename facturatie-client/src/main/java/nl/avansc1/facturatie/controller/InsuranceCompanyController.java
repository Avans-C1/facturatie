package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.administration.InsuranceCompany;
import nl.avansc1.facturatie.model.billing.Vat;
import nl.avansc1.facturatie.repository.InsuranceCompanyDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Matthijs on 13-10-2016.
 */
@Controller
@RequestMapping("/settings")
public class InsuranceCompanyController {
    private final Logger logger = LoggerFactory.getLogger(InsuranceCompanyController.class);

    @RequestMapping("")
    public String index(){
        return "settings/index";
    }
    /**
     * Overview page
     * @return template/insuranceCompany/overview.html
     */

    @RequestMapping("/new")
    public String add() {
        return "settings/add";
    }

    /**
     * Process the saving stuff
     * @return saved message is the insuranceCompany is saved
     */

    @RequestMapping("/update/{id}")
    public String update() {
        return "settings/update";
    }

    /**
     * Process the update stuff
     * @return saved message is the insuranceCompany is updated
     */

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addHandler(int id, String name, String houseNumber, String zipcode, String city,
                             int phoneNumber, String email, int kvkNumber, Vat vat) {
        try {
            InsuranceCompany insuranceCompany = new InsuranceCompany(id, name, houseNumber, zipcode, city, phoneNumber, email, kvkNumber, vat);
            insuranceCompanyDAO.save(insuranceCompany);
        } catch (Exception ex) {
            return "saving error";
        }

        return "saved!";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInsuranceCompany(Model model, @PathVariable int id) {
        logger.debug("InsuranceCompany, id = " + id);
        try {
            InsuranceCompany insuranceCompany = insuranceCompanyDAO.findOne(id);
            //insuranceCompany.setName();
            //insuranceCompany.setCity();
            //insuranceCompany.setEmail();
            //insuranceCompany.setHouseNumber();
            //insuranceCompany.setKvkNumber();
            //insuranceCompany.setZipcode();
            //insuranceCompany.setPhoneNumber();
            //insuranceCompany.setVat();
            //!!!!beschrijven wat er gedaan moet worden -> updaten van waarden -> dus ophalen..

            //Save insuranceCompany
            this.insuranceCompanyDAO.save(insuranceCompany);
            //Get insuranceCompany from DAO
            Iterable<InsuranceCompany> insuranceList = insuranceCompanyDAO.findAll();
            //Add insuranceCompany to model
            model.addAttribute("insuranceCompany", insuranceList);
        } catch (Exception ex) {
            return "updating error";
        }
        // Open view
        return "settings/index";
    }

    @Autowired
    private InsuranceCompanyDAO insuranceCompanyDAO;
}
