package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.administration.InsuranceCompany;
import nl.avansc1.facturatie.model.billing.Declaration;
import nl.avansc1.facturatie.model.billing.Invoice;
import nl.avansc1.facturatie.model.billing.PaymentCondition;
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
    @Autowired
    private CustomerDAO customerDAO;

    /**
     * Returns page name for displaying invoices in a list
     *
     * @param theModel
     * @return Page name
     */
    @RequestMapping("")
    public String listInvoices(Model theModel) {
        //Get invoices from DAO
        Iterable<Invoice> invoiceList = invoiceDAO.findAll();

        //Add invoices to model
        theModel.addAttribute("invoices", invoiceList);
        theModel.addAttribute("paymentConditions", paymentConditionDAO.findAll());

        //Return page name
        return "invoice/index";
    }

    /**
     * Deletes invoice by given id in the path variable
     * @param model
     * @param id Invoice id
     * @return Page name
     */
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteDeclaration(Model model, @PathVariable int id) {
        logger.debug("Invoice, id = " + id);

        //Delete invoice with Id
        Invoice invoice = invoiceDAO.findOne(id);
        invoiceDAO.delete(invoice);

        //Add successful message to the model
        model.addAttribute("success", "Invoice removed!");

        //Return list with invoices page
        return this.listInvoices(model);
    }

    /**
     * Set payment day and status of a invoice to paid
     * @param model
     * @param id Invoice id
     * @return Page name
     */
    @RequestMapping(value = "/pay/{id}", method = RequestMethod.GET)
    public String payDeclaration(Model model, @PathVariable int id) {
        //Find invoice with given id
        Invoice invoice = invoiceDAO.findOne(id);

        //Set the date payed to today and set the state to paid
        invoice.setDatePayed(new Date());
        invoice.setState(1);

        //Save invoice
        this.invoiceDAO.save(invoice);

        //Add successful message to the model
        model.addAttribute("success", "Invoice marked as paid!");

        // Open view
        return this.listInvoices(model);
    }

    /**
     * Changes the payment condition of a certain invoice
     * @param model
     * @param id Invoice id
     * @param paymentConditionId Payment condition id to change into
     * @return Page name
     */
    @RequestMapping(value = "/{id}/change-payment-condition/{paymentConditionId}", method = RequestMethod.GET)
    public String changePaymenyCondition(Model model, @PathVariable int id, @PathVariable int paymentConditionId) {
        //Find invoice with given id
        Invoice invoice = invoiceDAO.findOne(id);

        //Set the given payment condition
        invoice.setPaymentCondition(paymentConditionDAO.findOne(paymentConditionId));

        //Save invoice
        this.invoiceDAO.save(invoice);

        //Add successful message to the model
        model.addAttribute("success", "Payment Condition updated!");

        // Open view
        return this.listInvoices(model);
    }

    /**
     * Print a certain invoice based on the given id
     * @param model
     * @param id Invoice id
     * @return Page for printing the invoice
     */
    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
    public String PrintInvoice(Model model, @PathVariable int id) {
        //Find all the needed info to print the invoice
        Invoice invoice = invoiceDAO.findOne(id);
        Customer customer = invoice.getCustomer();
        Policy policy = policyDAO.findByCustomer(customer);
        InsuranceCompany company = policy.getInsurance().getInsuranceCompany();
        List<Declaration> declarations = declarationDAO.findByCustomer(customer);

        //Add the general info to the model
        model.addAttribute("Invoice", invoice);
        model.addAttribute("Customer", customer);
        model.addAttribute("Company", company);
        model.addAttribute("Declarations", declarations);

        //Loop trough the declarations to get the subtotal
        double subTotal = 0;
        for (Declaration d : declarations) {
            subTotal += (d.getPrice() - d.getCompensated());
        }

        //Format for the decimal values
        DecimalFormat formatter = new DecimalFormat("€ #,##0.00");

        //Round up the vat amount
        double vatAmount = Math.round((subTotal * company.getVat().getPercentageAmount()) * 100.0) / 100.0;

        //Add the payment info to the model
        model.addAttribute("SubTotal", formatter.format(subTotal));
        model.addAttribute("VatAmount", formatter.format(vatAmount));
        model.addAttribute("Total", formatter.format((subTotal + vatAmount)));


        //Replace the temp fill in fields with the data
        String paymentCondition = invoice.getPaymentCondition().getTemplate();
        paymentCondition = paymentCondition.replace("%Amount%", formatter.format((subTotal + vatAmount)));
        paymentCondition = paymentCondition.replace("%period_in_days%", Integer.toString(invoice.getPaymentCondition().getPeriodInDays()));
        paymentCondition = paymentCondition.replace("%BankAccount%", company.getIban());
        paymentCondition = paymentCondition.replace("%Company_Name%", company.getCompanyname());
        paymentCondition = paymentCondition.replace("%Invoice_Ref%", Integer.toString(invoice.getId()));

        //Add the filled in condition to the model
        model.addAttribute("PaymentCondition", paymentCondition);

        //Return the view
        return "invoice/print";
    }

    /**
     * Generates a new invoice for a given customer
     * @param model
     * @param customerId Customer id
     * @param condition Payment condition id
     */
    @RequestMapping(value = "/create/{customerId}/{condition}", method = RequestMethod.GET)
    public String GenerateInvoice(Model model, @PathVariable int customerId, @PathVariable int condition) {
        //Find the customer given
        Customer customer = customerDAO.findByCsn(customerId);

        //Find the open decelerations
        List<Declaration> decelerations = declarationDAO.findByCustomerInvoice(customerId);

        //Find decelerations count already on invoice
        int coveredDecelerations = declarationDAO.findByCustomerAndInvoiceNotNullOrderById(customer).size();

        //Find the policy for the customer
        Policy policy = policyDAO.findByCustomer(customer);
        //Find the insurance company of the customer
        InsuranceCompany company = policy.getInsurance().getInsuranceCompany();
        //Find the payment condition
        PaymentCondition paymentCondition = paymentConditionDAO.findOne(condition);

        int needToCover = policy.getInsurance().getCoveredTreatments() - coveredDecelerations;

        float contributionToPay = (policy.getContribution() - policy.getContributionUsed());


        //Create an invoice if there are decelerations
        if (decelerations.size() > 0) {

            Invoice invoice = new Invoice();
            invoice.setCustomer(customer);
            invoice.setPaymentCondition(paymentCondition);
            invoice.setVat(company.getVat());
            invoice.setDateCreated(new Date());
            invoice.setState(1);
            invoiceDAO.save(invoice);

            for (Declaration d : decelerations) {
                if (needToCover > 0) {
                    d.setCompensated(d.getPrice());
                    needToCover -= 1;
                }
                if (d.getCompensated() <= 0) {
                    contributionToPay = (policy.getContribution() - policy.getContributionUsed());
                    if (contributionToPay <= 0) {
                        d.setCompensated(d.getPrice());
                    } else {
                        if ((contributionToPay - d.getPrice()) < 0) {
                            d.setCompensated(d.getPrice() - contributionToPay);
                            policy.setContributionUsed(policy.getContributionUsed() + contributionToPay);
                        } else {
                            d.setCompensated(0);
                            policy.setContributionUsed(policy.getContributionUsed() + d.getPrice());
                        }
                        policyDAO.save(policy);
                    }
                }
                d.setInvoice(invoice);
                declarationDAO.save(d);
            }
        }

        return listInvoices(model);
    }

    /**
     *
     * @return returns the module name
     */
    @ModelAttribute("page")
    public String module() {
        return "invoices";
    }
}
