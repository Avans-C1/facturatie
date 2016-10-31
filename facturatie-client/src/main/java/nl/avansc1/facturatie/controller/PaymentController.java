package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.billing.PaymentCondition;
import nl.avansc1.facturatie.repository.PaymentConditionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kevin on 11-10-2016.
 */
@Controller
@RequestMapping("/payment-condition")
public class PaymentController {
    private final Logger logger = LoggerFactory.getLogger(DeclarationController.class);
    @Autowired
    private PaymentConditionDAO paymentConditionDAO;

    /**
     * Returns list with excising payment condition
     *
     * @param theModel Spring model to attributes
     * @return Page name
     */
    @RequestMapping("")
    public String listpaymentConditions(Model theModel) {
        //Get paymentConditions from DAO
        Iterable<PaymentCondition> paymentConditionList = paymentConditionDAO.findAll();

        //Add paymentConditions to model
        theModel.addAttribute("paymentConditions", paymentConditionList);

        return "payment_condition/index";
    }

    /**
     * Delete's payment condition with specified id
     * @param model Spring model to attributes
     * @param id Payment condition id
     * @return Page name
     */
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
        return "payment_condition/index";
    }

    /**
     * Gets page to add new payment condition
     * @param model Spring model to attributes
     * @return Page name
     */
    @GetMapping("/add")
    public String addCondition(Model model) {
        model.addAttribute("paymentCondition", new PaymentCondition());
        return "payment_condition/add";
    }

    /**
     * Post page to create new payment condition
     * @param model Spring model to attributes
     * @param paymentCondition Filled in payment condition with form fields
     * @return Page name
     */
    @PostMapping("/add")
    public String addCondition(Model model, @ModelAttribute PaymentCondition paymentCondition) {

        this.paymentConditionDAO.save(paymentCondition);

        //Get paymentConditions from DAO
        Iterable<PaymentCondition> paymentConditionList = paymentConditionDAO.findAll();

        //Add paymentConditions to model
        model.addAttribute("paymentConditions", paymentConditionList);

        // Open view
        return "payment_condition/index";
    }

    /**
     * Edit payment condition of given id
     * @param model Spring model to attributes
     * @param id Id of payment condition
     * @return Page name
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCondition(Model model, @PathVariable int id) {
        PaymentCondition paymentCondition = paymentConditionDAO.findOne(id);
        model.addAttribute("paymentCondition", paymentCondition);
        return "payment_condition/add";
    }

    /**
     * Get's the module of the controller
     * @return Module name
     */
    @ModelAttribute("page")
    public String module() {
        return "payment-conditions";
    }
}
