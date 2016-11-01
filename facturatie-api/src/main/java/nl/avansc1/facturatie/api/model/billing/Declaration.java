package nl.avansc1.facturatie.api.model.billing;

import nl.avansc1.facturatie.api.model.customers.Customer;

import javax.persistence.*;
import java.util.Date;

/**
 * This is a object of a declaration
 *
 * @author Bob van der Valk
 */
@Entity
@Table(name ="declarations")
public class Declaration {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;
    @Temporal(TemporalType.DATE)
    @Column(name = "declarated_at")
    private Date declaredAt;
    @Column(name = "price")
    private float price;
    @Column(name = "compensated")
    private float compensated;

    public Declaration(int id, Customer customer, Treatment treatment, Date declaredAt, float price, float compensated) {
        this.id = id;
        this.customer = customer;
        this.treatment = treatment;
        this.declaredAt = declaredAt;
        this.price = price;
        this.compensated = compensated;
    }

    public Declaration(Customer customer, Treatment treatment, Date declaredAt, float price, float compensated) {
        this.customer = customer;
        this.treatment = treatment;
        this.declaredAt = declaredAt;
        this.price = price;
        this.compensated = compensated;
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

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
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

    public float getCompensated() {
        return compensated;
    }

    public void setCompensated(float compensated) {
        this.compensated = compensated;
    }
}
