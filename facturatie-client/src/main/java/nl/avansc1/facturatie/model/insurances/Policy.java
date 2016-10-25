package nl.avansc1.facturatie.model.insurances;

import nl.avansc1.facturatie.model.customers.Customer;

import javax.persistence.*;
import java.util.Date;


/**
 * This is a object of a policy.
 *
 *
 * @author Bob van der Valk, Matthijs Wilhelmus
 * @version 1.0
 * @see Customer
 * @see Insurance
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
    @Column(name = "date_start")
    private java.sql.Date dateStart;
    @Column(name = "date_end")
    private java.sql.Date dateEnd;
    @Column(name = "active")
    private boolean active;
    @Column(name = "contribution_used")
    private float contributionUsed;


    /**
     * Initializes a Policy for Database
     * @param id id of Policy will be used as primary key in Database
     * @param customer id of Customer will be used as foreign key in Database
     * @param insurance id of Insurance will be used as foreign key in Database
     * @param contribution amount of Contribution(Eigen risico)
     * @param dateStart start date of policy
     * @param dateEnd date on which the policy ends/expires
     * @param active indicates of policy is active or not
     * @param contributionUsed amount of Contribution that has been 'spent'
     */
    public Policy(int id, Customer customer, Insurance insurance, float contribution, java.sql.Date dateStart, java.sql.Date dateEnd, boolean active, float contributionUsed) {
        this.id = id;
        this.customer = customer;
        this.insurance = insurance;
        this.contribution = contribution;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.contributionUsed = contributionUsed;
        ;
    }

    public Policy(float contribution, Customer customer, Insurance insurance, java.sql.Date dateStart, java.sql.Date dateEnd, boolean active, float contributionUsed) {
        this.contribution = contribution;
        this.customer = customer;
        this.insurance = insurance;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.contributionUsed = contributionUsed;
        ;
    }

    public Policy() {
    }

    /**
     * Returns the id of this Policy
     * @return id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the contribution of this Policy
     * @return contribution
     */
    public float getContribution() {
        return contribution;
    }

    public void setContribution(float contribution) {
        this.contribution = contribution;
    }

    /**
     * Returns the customer belonging to this Policy
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Returns the insurance belonging to this Policy
     * @return insurance
     */
    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    /**
     * Returns the start date of this Policy
     * @return dateStart
     */
    public java.sql.Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(java.sql.Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Returns the end date(/expire date) of this Policy
     * @return dateEnd
     */
    public java.sql.Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(java.sql.Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Tells if this Policy ia active or not
     * @return active
     */
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the amount of Contribution that has been spent of this Policy
     * @return contributionUsed
     */
    public float getContributionUsed() {
        return contributionUsed;
    }

    public void setContributionUsed(float contributionUsed) {
        this.contributionUsed = contributionUsed;
    }

}
