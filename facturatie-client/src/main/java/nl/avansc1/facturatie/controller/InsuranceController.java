package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.insurances.Insurance;
import nl.avansc1.facturatie.repository.InsuranceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Matthijs on 13-10-2016.
 */
@Controller
@RequestMapping("/insurance")
public class InsuranceController {

    private InsuranceDAO insuranceDAO;

    @Autowired
    public InsuranceController(InsuranceDAO insuranceDAO) {
        this.insuranceDAO = insuranceDAO;
    }

    @ModelAttribute("page")
    public String module() {
        return "insurances";
    }


    // index

    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("insurances", insuranceDAO.findAll());

        return "insurance/index";
    }


    // create

    @GetMapping(value = "/create")
    String create(Model model) {
        model.addAttribute("insurance", new Insurance());

        return "insurance/edit";
    }

    @PostMapping(value = "/create")
    String store(Model model, @ModelAttribute Insurance insurance) {
        insuranceDAO.save(insurance);

        model.addAttribute("success", "Insurance successfully saved");

        return this.index(model);
    }


    // edit

    @GetMapping(value = "/edit/{id}")
    String edit(Model model, @PathVariable int id) {
        model.addAttribute("insurance", insuranceDAO.findOne(id));

        return "insurance/edit";
    }


    // delete

    @GetMapping(value = "/delete/{id}")
    String delete(Model model, @PathVariable int id) {
        try {
        insuranceDAO.delete(id);

        model.addAttribute("success", "Insurance successfully removed");
        } catch (Exception ex) {

            model.addAttribute("failure", "Insurance could not be removed - Is insurance linked to a Customer?");

        }

        return this.index(model);
    }
}
