package nl.avansc1.facturatie.model.billing;

import nl.avansc1.facturatie.model.customers.Customer;

import java.util.Date;

/**
 * This is a object of a declaration
 *
 * @author Bob van der Valk
 */
public class Declaration {
    private int id;
    private Customer customer;
    private Threatment threatment;
    private Invoice invoice;
    private Date declaredAt;
    private float price;

    public Declaration(int id, Customer customer, Threatment threatment, Invoice invoice, Date declaredAt, float price) {
        this.id = id;
        this.customer = customer;
        this.threatment = threatment;
        this.invoice = invoice;
        this.declaredAt = declaredAt;
        this.price = price;
    }

    public Declaration(Customer customer, Threatment threatment, Invoice invoice, Date declaredAt, float price) {
        this.customer = customer;
        this.threatment = threatment;
        this.invoice = invoice;
        this.declaredAt = declaredAt;
        this.price = price;
    }

    public Declaration() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Threatment getThreatment() {
        return threatment;
    }

    public void setThreatment(Threatment threatment) {
        this.threatment = threatment;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Date getDeclaredAt() {
        return declaredAt;
    }

    public void setDeclaredAt(Date declaredAt) {
        this.declaredAt = declaredAt;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
