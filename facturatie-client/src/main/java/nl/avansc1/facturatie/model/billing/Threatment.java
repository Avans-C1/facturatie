package nl.avansc1.facturatie.model.billing;

/**
 * This is the object of a treatment
 *
 * @author Bob van der Valk
 */
public class Threatment {
    private String id;
    private String name;
    private int duration;
    private float price;

    public Threatment(String id, String name, int duration, float price) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public Threatment(String name, int duration, float price) {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public Threatment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
