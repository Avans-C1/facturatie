package nl.avansc1.facturatie.model.administration;

import nl.avansc1.facturatie.model.billing.Vat;

import javax.persistence.*;

/**
 * This is the object of a insurance company
 *
 * @author Bob van der Valk, Matthijs Wilhelmus
 */
@Entity
@Table(name = "insurance_company")
public class InsuranceCompany {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String companyname;
    @Column(name = "street_name")
    private String name;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "city")
    private String city;
    @Column(name = "phonenumber")
    private int phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "kvk_number")
    private int kvkNumber;
    @OneToOne
    @JoinColumn(name = "vat_id")
    private Vat vat;
    @Column(name = "btw_number")
    private String btw;
    @Column(name = "iban")
    private String iban;

    public InsuranceCompany(int id, String companyname, String name, String houseNumber, String zipcode, String city, int phoneNumber, String email, int kvkNumber, Vat vat, String btw, String iban) {
        this.id = id;
        this.companyname = companyname;
        this.name = name;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.kvkNumber = kvkNumber;
        this.vat = vat;
        this.btw = btw;
        this.iban = iban;
    }

    public InsuranceCompany(String companyname, String name, String houseNumber, String zipcode, String city, int phoneNumber, String email, int kvkNumber, Vat vat, String btw, String iban) {
        this.companyname = companyname;
        this.name = name;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.kvkNumber = kvkNumber;
        this.vat = vat;
        this.btw = btw;
        this.iban = iban;
    }

    public InsuranceCompany() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
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

    public String getBtw() {
        return btw;
    }

    public void setBtw(String btw) {
        this.btw = btw;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

}
