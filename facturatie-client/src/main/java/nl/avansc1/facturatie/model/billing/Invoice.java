package nl.avansc1.facturatie.model.billing;

import nl.avansc1.facturatie.model.customers.Customer;

import java.util.Date;

/**
 * This is the object of a invoice
 *
 * @author Bob van der Valk
 */
public class Invoice {
    private int id;
    private Customer customer;
    private Vat vat;
    private Date dateCreated;
    private Date datePayed;
    private int state;
    private PaymentCondition paymentCondition;

    public Invoice(int id, Customer customer, Vat vat, Date dateCreated, Date datePayed, int state, PaymentCondition paymentCondition) {
        this.id = id;
        this.customer = customer;
        this.vat = vat;
        this.dateCreated = dateCreated;
        this.datePayed = datePayed;
        this.state = state;
        this.paymentCondition = paymentCondition;
    }

    public Invoice(Customer customer, Vat vat, Date dateCreated, Date datePayed, int state, PaymentCondition paymentCondition) {
        this.customer = customer;
        this.vat = vat;
        this.dateCreated = dateCreated;
        this.datePayed = datePayed;
        this.state = state;
        this.paymentCondition = paymentCondition;
    }

    public Invoice() {
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

    public Vat getVat() {
        return vat;
    }

    public void setVat(Vat vat) {
        this.vat = vat;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDatePayed() {
        return datePayed;
    }

    public void setDatePayed(Date datePayed) {
        this.datePayed = datePayed;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public PaymentCondition getPaymentCondition() {
        return paymentCondition;
    }

    public void setPaymentCondition(PaymentCondition paymentCondition) {
        this.paymentCondition = paymentCondition;
    }
}
