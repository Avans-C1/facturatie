package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.administration.InsuranceCompany;
import nl.avansc1.facturatie.model.billing.Declaration;
import nl.avansc1.facturatie.model.billing.Invoice;
import nl.avansc1.facturatie.model.customers.Customer;
import nl.avansc1.facturatie.model.insurances.Policy;
import nl.avansc1.facturatie.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DecimalFormat;
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
    @Autowired
    private InsuranceCompanyDAO insuranceCompanyDAO;
    @Autowired
    private PolicyDAO policyDAO;
    @Autowired
    private DeclarationDAO declarationDAO;

    @Autowired
    private PaymentConditionDAO paymentConditionDAO;

    @RequestMapping("")
    public String listInvoices(Model theModel) {
        //Get invoices from DAO
        Iterable<Invoice> invoiceList = invoiceDAO.findAll();

        //Add invoices to model
        theModel.addAttribute("invoices", invoiceList);
        theModel.addAttribute("paymentConditions", paymentConditionDAO.findAll());

        return "invoice/index";
    }

    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteDeclaration(Model model, @PathVariable int id) {
        logger.debug("Invoice, id = " + id);

        //Delete invoice with Id
        Invoice invoice = invoiceDAO.findOne(id);
        this.invoiceDAO.delete(invoice);

        model.addAttribute("success", "Invoice removed!");

        return this.listInvoices(model);
    }

    @RequestMapping(value = "/pay/{id}", method = RequestMethod.GET)
    public String payDeclaration(Model model, @PathVariable int id) {
        Invoice invoice = invoiceDAO.findOne(id);
        invoice.setDatePayed(new Date());
        invoice.setState(1);

        //Save invoice
        this.invoiceDAO.save(invoice);

        model.addAttribute("success", "Invoice marked as paid!");

        // Open view
        return this.listInvoices(model);
    }

    @RequestMapping(value = "/{id}/change-payment-condition/{paymentConditionId}", method = RequestMethod.GET)
    public String changePaymenyCondition(Model model, @PathVariable int id, @PathVariable int paymentConditionId) {
        Invoice invoice = invoiceDAO.findOne(id);
        invoice.setPaymentCondition(paymentConditionDAO.findOne(paymentConditionId));

        //Save invoice
        this.invoiceDAO.save(invoice);

        model.addAttribute("success", "Payment Condition updated!");

        // Open view
        return this.listInvoices(model);
    }

    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
    public String CreateInvoice(Model model, @PathVariable int id) {
        Invoice invoice = invoiceDAO.findOne(id);
        Customer customer = invoice.getCustomer();
        Policy policy = policyDAO.findByCustomer(customer);
        InsuranceCompany company = policy.getInsurance().getInsuranceCompany();
        List<Declaration> declarations = declarationDAO.findByCustomer(customer);

        model.addAttribute("Invoice", invoice);
        model.addAttribute("Customer", customer);
        model.addAttribute("Company", company);
        model.addAttribute("Declarations", declarations);

        double subTotal = 0;
        for (Declaration d : declarations) {
            subTotal += d.getPrice();
        }
        DecimalFormat formatter = new DecimalFormat("€#,##0.00");
        double vatAmount = Math.round((subTotal * company.getVat().getPercentageAmount()) * 100.0) / 100.0;
        model.addAttribute("SubTotal", formatter.format(subTotal));
        model.addAttribute("VatAmount", formatter.format(vatAmount));
        model.addAttribute("Total", formatter.format((subTotal + vatAmount)));
        String paymentCondition = invoice.getPaymentCondition().getTemplate();
        paymentCondition = paymentCondition.replace("%Amount%", formatter.format((subTotal + vatAmount)));
        paymentCondition = paymentCondition.replace("%period_in_days%", Integer.toString(invoice.getPaymentCondition().getPeriodInDays()));
        paymentCondition = paymentCondition.replace("%BankAccount%", "1234567890");
        paymentCondition = paymentCondition.replace("%Company_Name%", "Zilverenhuis");
        paymentCondition = paymentCondition.replace("%Invoice_Ref%", Integer.toString(invoice.getId()));

        model.addAttribute("PaymentCondition", paymentCondition);

        return "invoice/print";
    }

    @ModelAttribute("page")
    public String module() {
        return "invoices";
    }
}
