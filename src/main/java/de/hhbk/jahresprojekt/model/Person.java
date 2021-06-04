package de.hhbk.jahresprojekt.model;

import de.hhbk.jahresprojekt.views.annotations.TableField;

import javax.persistence.*;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @TableField
    private Long id;
    @TableField
    private String title;
    @TableField(label = "Vorname")
    private String firstName;
    @TableField
    private String lastName;
    @TableField
    private String phoneNumberMobile;
    @TableField
    private String phoneNumberLandline;
    @TableField
    private String email;
    @OneToOne
    private Address address;

    public Person() {
    }

    public Person(Long id, String title, String firstName, String lastName, String phoneNumberMobile, String phoneNumberLandline, String email, Address address) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumberMobile = phoneNumberMobile;
        this.phoneNumberLandline = phoneNumberLandline;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPhoneNumberMobile() {
        return phoneNumberMobile;
    }

    public void setPhoneNumberMobile(String phoneNumberMobile) {
        this.phoneNumberMobile = phoneNumberMobile;
    }

    public String getPhoneNumberLandline() {
        return phoneNumberLandline;
    }

    public void setPhoneNumberLandline(String phoneNumberLandline) {
        this.phoneNumberLandline = phoneNumberLandline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.join(", ", String.valueOf(id), getFirstName(), getLastName());
    }
}
