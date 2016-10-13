package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.billing.Invoice;
import nl.avansc1.facturatie.repository.InvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by kevin on 11-10-2016.
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceDAO invoiceDAO;

    @RequestMapping("")
    public String listInvoices(Model theModel) {

        System.out.println(invoiceDAO.toString());

        //Get invoices from DAO
        Iterable<Invoice> invoiceList = invoiceDAO.findAll();

        //Add invoices to model
        theModel.addAttribute("invoices", invoiceList);

        return "invoice/index";
    }

    @ModelAttribute("page")
    public String module() {
        return "invoices";
    }
}
