package nl.avansc1.facturatie.model.insurances;

import nl.avansc1.facturatie.model.customers.Customer;

import javax.persistence.*;
import java.util.Date;


/**
 * This is a object of a policy
 *
 * @author Bob van der Valk
 */
@Entity
@Table(name = "policies")
public class Policy {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    @Column(name = "contribution")
    private float contribution;
    //@Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private java.sql.Date dateStart;
    //@Temporal(TemporalType.DATE)
    @Column(name = "date_end")
    private java.sql.Date dateEnd;
    @Column(name = "active")
    private boolean active;
    @Column(name = "contributions_used")
    private float contributionsUsed;
    @Column(name = "contribution_used")
    private float contributionUsed;


    public Policy(int id, Customer customer, Insurance insurance, float contribution, java.sql.Date dateStart, java.sql.Date dateEnd, boolean active, float contributionsUsed, float contributionUsed) {
        this.id = id;
        this.customer = customer;
        this.insurance = insurance;
        this.contribution = contribution;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.contributionsUsed = contributionsUsed;
        this.contributionUsed = contributionUsed;
        ;
    }

    public Policy(float contribution, Customer customer, Insurance insurance, java.sql.Date dateStart, java.sql.Date dateEnd, boolean active, float contributionsUsed, float contributionUsed) {
        this.contribution = contribution;
        this.customer = customer;
        this.insurance = insurance;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.contributionsUsed = contributionsUsed;
        this.contributionUsed = contributionUsed;
        ;
    }

    public Policy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getContribution() {
        return contribution;
    }

    public void setContribution(float contribution) {
        this.contribution = contribution;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public java.sql.Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(java.sql.Date dateStart) {
        this.dateStart = dateStart;
    }

    public java.sql.Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(java.sql.Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getContributionsUsed() {
        return contributionsUsed;
    }

    public void setContributionsUsed(float contributionsUsed) {
        this.contributionsUsed = contributionsUsed;
    }

    public float getContributionUsed() {
        return contributionUsed;
    }

    public void setContributionUsed(float contributionUsed) {
        this.contributionUsed = contributionUsed;
    }

}
