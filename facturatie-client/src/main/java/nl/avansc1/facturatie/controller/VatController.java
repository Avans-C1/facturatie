package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.billing.Invoice;
import nl.avansc1.facturatie.model.billing.Vat;
import nl.avansc1.facturatie.repository.VatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String overview(Model theModel) {

        //Add invoices to model
        theModel.addAttribute("vats", getVatList());

        return "vat/overview";
    }

    @RequestMapping("/new")
    public String add() {
        return "vat/add";
    }


    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteVat(Model model, @PathVariable int id) {

        //Delete invoice with Id
        Vat vat = VatDAO.findOne(id);
        this.VatDAO.delete(vat);


        //Add invoices to model
        model.addAttribute("message", "Vat removed from the database");
        model.addAttribute("vats", getVatList());

        // Open view
        return "vat/overview";
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addVat(int percentage, final ModelMap model) {
        try {
            Vat vat = new  Vat(percentage);
            VatDAO.save(vat);

            model.addAttribute("message", "Vat added to the database");
            model.addAttribute("vats", getVatList());
            return "vat/overview";
        } catch (Exception ex) {
            return "vat/add";
        }

    }

    private Iterable<Vat> getVatList(){
        return  VatDAO.findAll();
    }



    @Autowired
    private VatDAO VatDAO;

}