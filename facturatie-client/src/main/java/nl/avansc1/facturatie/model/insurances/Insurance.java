package nl.avansc1.facturatie.model.insurances;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * This is the object of a Insurance
 *
 * @author Bob van der Valk
 */
@Entity
@Table(name = "insurances")
public class Insurance {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "monthlyFee")
    private float monthlyFee;
    @Column(name = "coveredTreatments")
    private int coveredTreatments;

    public Insurance(int id, String name, float monthlyFee, int coveredTreatments) {
        this.id = id;
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.coveredTreatments = coveredTreatments;
    }

    public Insurance(String name, float monthlyFee, int coveredTreatments) {
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.coveredTreatments = coveredTreatments;
    }

    public Insurance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public int getCoveredTreatments() {
        return coveredTreatments;
    }

    public void setCoveredTreatments(int coveredTreatments) {
        this.coveredTreatments = coveredTreatments;
    }
}
