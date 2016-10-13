package nl.avansc1.facturatie.model.insurances;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

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
    private int id;
    @Column(name = "contribution")
    private float contribution;
    @Column(name = "dateStart")
    private Date dateStart;
    @Column(name = "dateEnd")
    private Date dateEnd;
    @Column(name = "active")
    private boolean active;
    @Column(name = "contributionUsed")
    private float contributionUsed;
    @Column(name = "insurances")
    private List<Insurance> insurances;

    public Policy(int id, float contribution, Date dateStart, Date dateEnd, boolean active, float contributionUsed, List<Insurance> insurances) {
        this.id = id;
        this.contribution = contribution;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.contributionUsed = contributionUsed;
        this.insurances = insurances;
    }

    public Policy(float contribution, Date dateStart, Date dateEnd, boolean active, float contributionUsed, List<Insurance> insurances) {
        this.contribution = contribution;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.contributionUsed = contributionUsed;
        this.insurances = insurances;
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getContributionUsed() {
        return contributionUsed;
    }

    public void setContributionUsed(float contributionUsed) {
        this.contributionUsed = contributionUsed;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }
}
