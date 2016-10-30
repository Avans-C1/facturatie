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
    private String streetname;
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

    /**
     * Initializes an InsuranceCompany for Database
     * @param id id of Insurance Company will be used as primary key in Database
     * @param companyname name of the Insurance Company
     * @param streetname  street name of the Insurance Company
     * @param houseNumber house number of the Insurance Company
     * @param zipcode Zip Code of the Insurance Company
     * @param city name of the city where the Insurance Company is located
     * @param phoneNumber phone number of the Insurance Company
     * @param email email address of the Insurance Company
     * @param kvkNumber kvk number of the Insurance Company
     * @param vat id of a vat percentage will be used as foreign key in Database.
     * @param btw VAT number of the Insurance Company
     * @param iban IBAN of the Insurance Company
     */
    public InsuranceCompany(int id, String companyname, String streetname, String houseNumber, String zipcode, String city, int phoneNumber, String email, int kvkNumber, Vat vat, String btw, String iban) {
        this.id = id;
        this.companyname = companyname;
        this.streetname = streetname;
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

    public InsuranceCompany(String companyname, String streetname, String houseNumber, String zipcode, String city, int phoneNumber, String email, int kvkNumber, Vat vat, String btw, String iban) {
        this.companyname = companyname;
        this.streetname = streetname;
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

    /**
     * Returns the id of this Insurance Company
     * @return id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the Company name of this Insurance Company
     * @return companyname
     */
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    /**
     * Returns the street name of this Insurance Company
     * @return streetname
     */
    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    /**
     * Returns house number of this Insurance Company
     * @return houseNumber
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Returns zip code of this Insurance Company
     * @return zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Returns the city where this Insurance Company is located
     * @return city
     */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the phone number of this Insurance Company
     * @return phoneNumber
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the email address of this Insurance Company
     * @return email
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns kvk number of this Insurance Company
     * @return kvkNumber
     */
    public int getKvkNumber() {
        return kvkNumber;
    }

    public void setKvkNumber(int kvkNumber) {
        this.kvkNumber = kvkNumber;
    }

    /**
     * Returns the vat of this Insurance Company
     * @return vat
     */
    public Vat getVat() {
        return vat;
    }

    public void setVat(Vat vat) {
        this.vat = vat;
    }

    /**
     * Returns the btw number of this Insurance Company
     * @return btw
     */
    public String getBtw() {
        return btw;
    }

    public void setBtw(String btw) {
        this.btw = btw;
    }

    /**
     * Returns the IBAN of this Insurance Company
     * @return iban
     */
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

}
