package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.repository.InvoiceDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kevin on 11-10-2016.
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    //@Autowired
    private InvoiceDAO invoiceDAO;

    @RequestMapping("/list")
    public String listInvoices(Model theModel) {

        System.out.println(invoiceDAO.toString());

        //Get invoices from DAO
        //Invoice invoiceList = invoiceDAO.getInvoice(1);

        //Add invoices to model
        //theModel.addAttribute("invoices", invoiceList);

        return "invoice/list-invoices";
    }
}