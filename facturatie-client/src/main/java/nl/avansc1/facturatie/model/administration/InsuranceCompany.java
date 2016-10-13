package nl.avansc1.facturatie.model.administration;

import nl.avansc1.facturatie.model.billing.Vat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is the object of a insurance company
 *
 * @author Bob van der Valk
 */
@Entity
@Table(name = "insuranceCompany")
public class InsuranceCompany {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "houseNumber")
    private String houseNumber;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "city")
    private String city;
    @Column(name = "phoneNumber")
    private int phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "kvkNumber")
    private int kvkNumber;
    @Column(name = "vat")
    private Vat vat;

    public InsuranceCompany(int id, String name, String houseNumber, String zipcode, String city, int phoneNumber, String email, int kvkNumber, Vat vat) {
        this.id = id;
        this.name = name;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.kvkNumber = kvkNumber;
        this.vat = vat;
    }

    public InsuranceCompany(String name, String houseNumber, String zipcode, String city, int phoneNumber, String email, int kvkNumber, Vat vat) {
        this.name = name;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.kvkNumber = kvkNumber;
        this.vat = vat;
    }

    public InsuranceCompany() {
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getKvkNumber() {
        return kvkNumber;
    }

    public void setKvkNumber(int kvkNumber) {
        this.kvkNumber = kvkNumber;
    }

    public Vat getVat() {
        return vat;
    }

    public void setVat(Vat vat) {
        this.vat = vat;
    }
}
