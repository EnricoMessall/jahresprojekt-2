package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.Person;

public final class PersonBuilder {
    private int id;
    private String title;
    private String firstName;
    private String lastName;
    private String phoneNumberMobile;
    private String phoneNumberLandline;
    private String email;

    private PersonBuilder() {
    }

    public static PersonBuilder aPerson() {
        return new PersonBuilder();
    }

    public PersonBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public PersonBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PersonBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder withPhoneNumberMobile(String phoneNumberMobile) {
        this.phoneNumberMobile = phoneNumberMobile;
        return this;
    }

    public PersonBuilder withPhoneNumberLandline(String phoneNumberLandline) {
        this.phoneNumberLandline = phoneNumberLandline;
        return this;
    }

    public PersonBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public Person build() {
        return new Person(id, title, firstName, lastName, phoneNumberMobile, phoneNumberLandline, email);
    }
}