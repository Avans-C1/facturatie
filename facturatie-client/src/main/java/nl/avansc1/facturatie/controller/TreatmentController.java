package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.billing.Invoice;
import nl.avansc1.facturatie.model.billing.Treatment;
import nl.avansc1.facturatie.model.billing.Vat;
import nl.avansc1.facturatie.repository.TreatmentDAO;
import nl.avansc1.facturatie.repository.VatDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kevin on 11-10-2016.
 */
@Controller
@RequestMapping("/treatments")
public class TreatmentController {
    private final Logger logger = LoggerFactory.getLogger(DeclarationController.class);
    @Autowired
    private TreatmentDAO TreatmentDAO;

    @RequestMapping("")
    public String listTreatments(Model model) {

        //Add invoices to model
        model.addAttribute("Treatments", getTreatmentList());

        return "treatments/index";
    }

    private Iterable<Treatment> getTreatmentList(){
        return  TreatmentDAO.findAll();
    }


    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteTreatment(Model model, @PathVariable int id) {


        //Delete invoice with Id
        Treatment treatment = TreatmentDAO.findOne(id);
        TreatmentDAO.delete(treatment);



        //Add invoices to model
        model.addAttribute("message", "Treatment removed from the database");
        model.addAttribute("Treatments", getTreatmentList());

        // Open view
        return "treatments/index";
    }

    @RequestMapping("/new")
    public String add() {
        return "treatments/create";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addTreatment(String name, int duration, float price, @RequestParam(value = "id", required=false, defaultValue = "0") int id, final ModelMap model) {
        try {

            if(id>0){
                //update
                Treatment treatment = TreatmentDAO.findOne(id);
                treatment.setDuration(duration);
                treatment.setPrice(price);
                treatment.setName(name);

                TreatmentDAO.save(treatment);
                model.addAttribute("message", "Treatment aangepast");
            }else{
                //add
                Treatment treatment = new  Treatment(name, duration, price);
                TreatmentDAO.save(treatment);

                model.addAttribute("message", "Treatment added to the database");
            }

            model.addAttribute("Treatments", getTreatmentList());
            return "treatments/index";
        } catch (Exception ex) {
            return "treatments/create";
        }

    }

    @RequestMapping(value = "/new/{id}" , method = RequestMethod.GET)
    public String editTreatment(Model model, @PathVariable int id) {
        Treatment treatment = TreatmentDAO.findOne(id);
        System.out.println("Treatment id: " + id + " Name: " + treatment.getName());
        if(treatment!=null){

            model.addAttribute("Treatment", treatment);
            return "treatments/create";
        }else{
            return "treatments/index";
        }
    }




    @ModelAttribute("page")
    public String module() {
        return "treatments";
    }
}
