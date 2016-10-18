package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.billing.Vat;
import nl.avansc1.facturatie.repository.VatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Pascal van Hoof on 13-10-2016.
 */


@Controller
@RequestMapping("/vat")
public class VatController {

    @ModelAttribute("page")
    public String module() {
        return "vat";
    }

    /**
     * Overview page
     * @return template/customer/index.html
     */
    @RequestMapping("")
    public String index(Model theModel) {

        //Add invoices to model
        theModel.addAttribute("vats", getVatList());

        return "vat/index";
    }

    @RequestMapping("/new")
    public String add(Model model) {
        model.addAttribute("vat", new Vat());

        return "vat/add";
    }


    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteDeclaration(Model model, @PathVariable int id) {
        this.vatDAO.delete(id);

        model.addAttribute("success", "Vat removed from the database");

        return this.index(model);
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addHandler(int percentage, Model model) {
        try {
            Vat vat = new  Vat(percentage);
            vatDAO.save(vat);

            model.addAttribute("success", "Vat added to the database");

            return this.index(model);
        } catch (Exception ex) {
            return "vat/add";
        }
    }

    private Iterable<Vat> getVatList(){
        return  vatDAO.findAll();
    }



    @Autowired
    private VatDAO vatDAO;

}
