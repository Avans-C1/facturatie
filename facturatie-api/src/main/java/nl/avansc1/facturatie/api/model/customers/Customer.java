package nl.avansc1.facturatie.api.model.customers;

import java.util.Date;

/**
 * This is a object of a customer
 *
 * @author Bob van der Valk
 */
public class Customer {
    private int csn;
    private String firstName;
    private String lastName;
    private String streetName;
    private String houseNumber;
    private String zipcode;
    private String city;
    private Date dateOfBirth;
    private int phoneNumber;
    private String email;
    private String iban;

    public Customer(int csn, String firstName, String lastName, String streetName, String houseNumber, String zipcode,
                    String city, Date dateOfBirth, int phoneNumber, String email, String iban) {
        this.csn = csn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.iban = iban;
    }

    public Customer(String firstName, String lastName, String streetName, String houseNumber, String zipcode,
                    String city, Date dateOfBirth, int phoneNumber, String email, String iban) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.iban = iban;
    }

    public Customer() {
    }

    public int getCsn() {
        return csn;
    }

    public void setCsn(int csn) {
        this.csn = csn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
