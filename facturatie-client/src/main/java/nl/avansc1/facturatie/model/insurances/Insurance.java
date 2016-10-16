package nl.avansc1.facturatie.model.insurances;

import nl.avansc1.facturatie.model.administration.InsuranceCompany;

import javax.persistence.*;


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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "insurance_company_id")
    private InsuranceCompany insuranceCompany;
    @Column(name = "name")
    private String name;
    @Column(name = "monthly_fee")
    private float monthlyFee;
    @Column(name = "covered_treatments")
    private int coveredTreatments;

    public Insurance(int id, InsuranceCompany insuranceCompany, String name, float monthlyFee, int coveredTreatments) {
        this.id = id;
        this.insuranceCompany = insuranceCompany;
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.coveredTreatments = coveredTreatments;
    }

    public Insurance(InsuranceCompany insuranceCompany, String name, float monthlyFee, int coveredTreatments) {
        this.insuranceCompany = insuranceCompany;
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

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
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
