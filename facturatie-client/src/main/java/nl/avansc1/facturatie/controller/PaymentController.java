package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.billing.PaymentCondition;
import nl.avansc1.facturatie.repository.PaymentConditionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by kevin on 11-10-2016.
 */
@Controller
@RequestMapping("/payments")
public class PaymentController {
    private final Logger logger = LoggerFactory.getLogger(DeclarationController.class);
    @Autowired
    private PaymentConditionDAO paymentConditionDAO;

    @RequestMapping("")
    public String listpaymentConditions(Model theModel) {

        System.out.println(paymentConditionDAO.toString());

        //Get paymentConditions from DAO
        Iterable<PaymentCondition> paymentConditionList = paymentConditionDAO.findAll();

        //Add paymentConditions to model
        theModel.addAttribute("paymentConditions", paymentConditionList);

        return "payments/index";
    }

    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteDeclaration(Model model, @PathVariable int id) {
        logger.debug("paymentCondition, id = " + id);

        //Delete paymentCondition with Id
        PaymentCondition paymentCondition = paymentConditionDAO.findOne(id);
        this.paymentConditionDAO.delete(paymentCondition);

        //Get paymentConditions from DAO
        Iterable<PaymentCondition> paymentConditionList = paymentConditionDAO.findAll();

        //Add paymentConditions to model
        model.addAttribute("paymentConditions", paymentConditionList);

        // Open view
        return "payments/index";
    }

    @GetMapping("/add")
    public String addCondition(Model model) {
        model.addAttribute("paymentCondition", new PaymentCondition());
        return "payments/add";
    }

    @PostMapping("/add")
    public String addCondition(Model model, @ModelAttribute PaymentCondition paymentCondition) {

        this.paymentConditionDAO.save(paymentCondition);

        //Get paymentConditions from DAO
        Iterable<PaymentCondition> paymentConditionList = paymentConditionDAO.findAll();

        //Add paymentConditions to model
        model.addAttribute("paymentConditions", paymentConditionList);

        // Open view
        return "payments/index";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCondition(Model model, @PathVariable int id) {
        PaymentCondition paymentCondition = paymentConditionDAO.findOne(id);
        model.addAttribute("paymentCondition", paymentCondition);
        return "payments/add";
    }

    @ModelAttribute("page")
    public String module() {
        return "payment-conditions";
    }
}
