package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.insurances.Insurance;
import nl.avansc1.facturatie.model.insurances.Policy;
import nl.avansc1.facturatie.repository.PolicyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by Matthijs on 13-10-2016.
 */
@Controller
@RequestMapping("/policy")
public class PolicyController {

    private PolicyDAO policyDAO;

    @Autowired
    public PolicyController(PolicyDAO policyDAO) {
        this.policyDAO = policyDAO;
    }

    @ModelAttribute("page")
    public String module() {
        return "policies";
    }


    // index

    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("policies", policyDAO.findAll());

        return "policy/index";
    }


    // create

    @GetMapping(value = "/create")
    String create(Model model) {
        model.addAttribute("policy", new Policy());

        return "policy/edit";
    }

    @PostMapping(value = "/create")
    String store(Model model, @ModelAttribute Policy policy) {
        policyDAO.save(policy);

        model.addAttribute("success", "Policy successfully saved");

        return this.index(model);
    }


    // edit

    @GetMapping(value = "/edit/{id}")
    String edit(Model model, @PathVariable int id) {
        model.addAttribute("policy", policyDAO.findOne(id));

        return "policy/edit";
    }


    // delete

    @GetMapping(value = "/delete/{id}")
    String delete(Model model, @PathVariable int id) {
        policyDAO.delete(id);

        model.addAttribute("success", "Policy successfully removed");

        return this.index(model);
    }
}
