package nl.avansc1.facturatie.model.insurances;

/**
 * This is the object of a Insurance
 *
 * @author Bob van der Valk
 */
public class Insurance {
    private int id;
    private String name;
    private float monthlyFee;
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
