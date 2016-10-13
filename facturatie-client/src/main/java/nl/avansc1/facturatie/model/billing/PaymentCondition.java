package nl.avansc1.facturatie.model.billing;

import javax.persistence.*;

/**
 * This is a object of a payment condition
 *
 * @author Bob van der Valk
 */
@Entity
@Table(name ="payment_conditions")
public class PaymentCondition {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "period_in_days")
    private int periodInDays;

    public PaymentCondition(int id, String name, int periodInDays) {
        this.id = id;
        this.name = name;
        this.periodInDays = periodInDays;
    }

    public PaymentCondition(String name, int periodInDays) {
        this.name = name;
        this.periodInDays = periodInDays;
    }

    public PaymentCondition() {
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

    public int getPeriodInDays() {
        return periodInDays;
    }

    public void setPeriodInDays(int periodInDays) {
        this.periodInDays = periodInDays;
    }
}
