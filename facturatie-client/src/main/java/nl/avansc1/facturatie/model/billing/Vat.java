package nl.avansc1.facturatie.model.billing;

/**
 * This is a vat object
 *
 * @author Bob van der Valk
 */
public class Vat {
    private int id;
    private double percentage;

    public Vat(int id, double percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public Vat(double percentage) {
        this.percentage = percentage;
    }

    public Vat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
