package de.hhbk.jahresprojekt.model;

public class Person {
    private Long id;
    private String title;
    private String firstName;
    private String lastName;
    private String phoneNumberMobile;
    private String phoneNumberLandline;
    private String email;

    public Person() {
    }

    public Person(Long id, String title, String firstName, String lastName, String phoneNumberMobile, String phoneNumberLandline, String email) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumberMobile = phoneNumberMobile;
        this.phoneNumberLandline = phoneNumberLandline;
        this.email = email;
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
}
