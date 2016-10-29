package nl.avansc1.facturatie.api.model.billing;

import javax.persistence.*;

/**
 * This is the object of a treatment
 *
 * @author Bob van der Valk
 */
@Entity
@Table(name ="treatments")
public class Treatment {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private int duration;
    @Column(name = "price")
    private float price;

    public Treatment(int id, String name, int duration, float price) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public Treatment(String name, int duration, float price) {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public Treatment() {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

