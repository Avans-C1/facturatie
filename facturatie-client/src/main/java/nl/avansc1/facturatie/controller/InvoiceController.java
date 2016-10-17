package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.billing.Invoice;
import nl.avansc1.facturatie.repository.InvoiceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by kevin on 11-10-2016.
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    private final Logger logger = LoggerFactory.getLogger(DeclarationController.class);
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

    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteDeclaration(Model model, @PathVariable int id) {
        logger.debug("Invoice, id = " + id);

        //Delete invoice with Id
        Invoice invoice = invoiceDAO.findOne(id);
        this.invoiceDAO.delete(invoice);

        //Get invoices from DAO
        Iterable<Invoice> invoiceList = invoiceDAO.findAll();

        //Add invoices to model
        model.addAttribute("invoices", invoiceList);

        // Open view
        return "invoice/index";
    }

    @RequestMapping(value = "/pay/{id}", method = RequestMethod.GET)
    public String payDeclaration(Model model, @PathVariable int id) {
        logger.debug("Invoice, id = " + id);

        Invoice invoice = invoiceDAO.findOne(id);
        invoice.setDatePayed(new Date());
        invoice.setState(1);

        //Save invoice
        this.invoiceDAO.save(invoice);

        //Get invoices from DAO
        Iterable<Invoice> invoiceList = invoiceDAO.findAll();

        //Add invoices to model
        model.addAttribute("invoices", invoiceList);

        // Open view
        return "invoice/index";
    }

    @ModelAttribute("page")
    public String module() {
        return "invoices";
    }
}
