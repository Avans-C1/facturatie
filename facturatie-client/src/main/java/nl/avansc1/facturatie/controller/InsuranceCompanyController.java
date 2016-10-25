package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.administration.InsuranceCompany;
import nl.avansc1.facturatie.repository.InsuranceCompanyDAO;
import nl.avansc1.facturatie.repository.VatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Matthijs Wilhelmus on 13-10-2016.
 *
 * This ia a controller for <code>InsuranceCompany</code>.
 * This controller regulates the mapping of the Settings(insuranceCompany) page
 * for viewing the current insurance Company data as well as for updating this data.
 * This controller uses the linked <code>InsuranceCompanyDAO</code> and <code>VatDAO</code>.
 *
 * @author Matthijs Wilhelmus
 * @version 1.0
 * @see InsuranceCompanyDAO
 * @see InsuranceCompany
 * @see VatDAO
 */
@Controller
@RequestMapping("/insurance_company")
public class InsuranceCompanyController {

    @Autowired
    private InsuranceCompanyDAO insuranceCompanyDAO;

    @Autowired
    private VatDAO vatDAO;

    @ModelAttribute("page")
    public String module() {
        return "insurance-companies";
    }


    // index

    /**
     * Mapping for index page
     * @param model
     * @return
     * @deprecated Not in use
     */
    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("insuranceCompany", insuranceCompanyDAO.findOne(1));
        model.addAttribute("vats", vatDAO.findAll());

        return "insurance_company/edit";
    }


    // create

    /**
     *
     * @deprecated There is no need for creating multiple InsuranceCompanies for there is only one.
     */
    @GetMapping(value = "/create")
    String create(Model model) {
        model.addAttribute("insuranceCompany", new InsuranceCompany());
        model.addAttribute("vats", vatDAO.findAll());

        return "insurance_company/edit";
    }

    /**
     *
     * @deprecated There is no need for creating multiple InsuranceCompanies for there is only one.
     */
    @PostMapping(value = "/create")
    String store(Model model, @ModelAttribute InsuranceCompany insuranceCompany) {
        insuranceCompanyDAO.save(insuranceCompany);

        model.addAttribute("success", "Insurance Company successfully saved");

        return this.index(model);
    }


    // edit

    /**
     * Mapping for editing the current InsuranceCompany.
     * @param model
     * @param id
     * @return insurance_company/edit
     */
    @GetMapping(value = "/edit/{id}")
    String edit(Model model, @PathVariable int id) {
        model.addAttribute("insuranceCompany", insuranceCompanyDAO.findOne(id));
        model.addAttribute("vats", vatDAO.findAll());
        model.addAttribute("success", "Insurance Company successfully saved");

        return "insurance_company/edit";
    }


    // delete

    /**
     * @deprecated The current InsuranceCompany cannot be deleted only updated.
     */
    @GetMapping(value = "/delete/{id}")
    String delete(Model model, @PathVariable int id) {
        insuranceCompanyDAO.delete(id);

        model.addAttribute("success", "Insurance Company successfully removed");

        return this.index(model);
    }
}
