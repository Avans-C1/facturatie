package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.administration.InsuranceCompany;
import nl.avansc1.facturatie.repository.InsuranceCompanyDAO;
import nl.avansc1.facturatie.repository.VatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Matthijs on 13-10-2016.
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
        return "insuranceCompanies";
    }


    // index

    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("insuranceCompanies", insuranceCompanyDAO.findAll());

        return "insurance_company/index";
    }


    // create

    @GetMapping(value = "/create")
    String create(Model model) {
        model.addAttribute("insuranceCompany", new InsuranceCompany());
        model.addAttribute("vats", vatDAO.findAll());

        return "insurance_company/edit";
    }

    @PostMapping(value = "/create")
    String store(Model model, @ModelAttribute InsuranceCompany insuranceCompany) {
        insuranceCompanyDAO.save(insuranceCompany);

        model.addAttribute("success", "Insurance Company successfully saved");

        return this.index(model);
    }


    // edit

    @GetMapping(value = "/edit/{id}")
    String edit(Model model, @PathVariable int id) {
        model.addAttribute("insuranceCompany", insuranceCompanyDAO.findOne(id));
        model.addAttribute("vats", vatDAO.findAll());

        return "insurance_company/edit";
    }


    // delete

    @GetMapping(value = "/delete/{id}")
    String delete(Model model, @PathVariable int id) {
        insuranceCompanyDAO.delete(id);

        model.addAttribute("success", "Insurance Company successfully removed");

        return this.index(model);
    }
}
