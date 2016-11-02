package nl.avansc1.facturatie;

import nl.avansc1.facturatie.controller.CustomerController;
import nl.avansc1.facturatie.model.billing.Invoice;
import nl.avansc1.facturatie.model.billing.PaymentCondition;
import nl.avansc1.facturatie.model.billing.Vat;
import nl.avansc1.facturatie.model.customers.Customer;
import nl.avansc1.facturatie.repository.InvoiceDAO;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * {message}
 *
 * @author Bob van der Valk
 */
@Configuration
public class AppConfig {
    public CustomerController customerController() {
        return Mockito.mock(CustomerController.class);
    }

    @Bean
    public InvoiceDAO invoiceDAO() {
        Vat testVat = new Vat(1, 21.0);
        PaymentCondition testPaymentCondition = new PaymentCondition("Test", "test template", 12);
        Customer testCustomer = new Customer(1, "Kevin", "Bos", "Vijfhagen", "209", "4812XT", "Breda", "2016-01-01", "0638178232", "k.bos@test.com", "NL37Rabo028383292");

        Invoice testInvoiceNotPaid = new Invoice(1, null, testVat, new Date(), null, 0, testPaymentCondition);
        Invoice testInvoicePaid = new Invoice(1, null, testVat, new Date(), new Date(), 1, testPaymentCondition);

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(testInvoiceNotPaid);
        invoices.add(testInvoicePaid);

        InvoiceDAO invoiceDAO = Mockito.mock(InvoiceDAO.class);
        when(invoiceDAO.findAll()).thenReturn(invoices);
        when(invoiceDAO.findOne(1)).thenReturn(invoices.get(0));
        when(invoiceDAO.findOne(2)).thenReturn(invoices.get(1));
        return invoiceDAO;
    }
}
