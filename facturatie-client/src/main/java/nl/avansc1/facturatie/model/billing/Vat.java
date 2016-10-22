package nl.avansc1.facturatie.model.billing;

import javax.persistence.*;

/**
 * This is a vat object
 *
 * @author Bob van der Valk
 */
@Entity
@Table(name ="vat")
public class Vat {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "percentage")
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

    public String getPercentage() {
        return percentage + "%";
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentageAmount() {
        return percentage / 100;
    }
}
