package nl.avansc1.facturatie.model.billing;

import nl.avansc1.facturatie.model.customers.Customer;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * This is the object of a invoice
 *
 * @author Bob van der Valk
 */
@Entity
@Table(name ="invoices")
public class Invoice {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "vat_id")
    private Vat vat;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_created")
    private Date dateCreated;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_payed")
    private Date datePayed;
    @Column(name = "state")
    private int state;
    @OneToOne
    @JoinColumn(name = "payment_condition_id")
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

    public String getStateInText() {
        switch (this.state) {
            case 1:
                return "Paid";
            case 0:
            default:
                return "Not payed";
        }
    }

    public String getUserFriendlyDatePayed() {
        if (this.datePayed == null) {
            return "";
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.datePayed);
    }

    public PaymentCondition getPaymentCondition() {
        return paymentCondition;
    }

    public void setPaymentCondition(PaymentCondition paymentCondition) {
        this.paymentCondition = paymentCondition;
    }

    public boolean isOverdue() {
        Date start = new Date();

        if (this.datePayed != null) {
            start = this.datePayed;
        }

        Date created = this.dateCreated;

        long duration  = start.getTime() - created.getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);

        return diffInDays > this.paymentCondition.getPeriodInDays();
    }

    public String getDateToBePayed() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateCreated);
        cal.add(Calendar.DATE, paymentCondition.getPeriodInDays());
        return df.format(cal.getTime());
    }
}
