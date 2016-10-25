package nl.avansc1.facturatie.model.insurances;

import nl.avansc1.facturatie.model.administration.InsuranceCompany;

import javax.persistence.*;


/**
 * This is the object of an Insurance.
 *
 * @author Bob van der Valk, Matthijs Wilhelmus
 * @version 1.0
 * @see InsuranceCompany
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

    /**
     * Initializes an Insurance for Database
     * @param id of Insurance will be used as primary key in Database
     * @param insuranceCompany id of InsuranceCompany will be used as foreign key inn database
     * @param name of the Insurance
     * @param monthlyFee price of Insurance per month
     * @param coveredTreatments amount of treatments covered by this Insurance
     */
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

    /**
     * Returns the id of this Insurance
     * @return id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the InsuranceCompany of this Insurance
     * @return insuranceCompany
     */
    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    /**
     * Returns the name of this Insurance
     * @return name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price per month of this Insurance
     * @return monthlyFee
     */
    public float getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    /**
     * Returns the number of covered treatments of this Insurance
     * @return coveredTreatments
     */
    public int getCoveredTreatments() {
        return coveredTreatments;
    }

    public void setCoveredTreatments(int coveredTreatments) {
        this.coveredTreatments = coveredTreatments;
    }
}
