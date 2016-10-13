package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.billing.Invoice;

import java.util.List;

/**
 * Created by kevin on 11-10-2016.
 */
public interface InvoiceDAO {

    public List<Invoice> getInvoices();

    public Invoice getInvoice(int id);

    public boolean deleteInvoice(int id);

    public boolean addInvoice(Invoice invoice);

    public boolean updateInvoice(Invoice invoice);
}
