package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.Adress;

public final class AdressBuilder {
    private int id;
    private String street;
    private String zipCode;
    private String city;
    private String country;

    private AdressBuilder() {
    }

    public static AdressBuilder anAdress() {
        return new AdressBuilder();
    }

    public AdressBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public AdressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AdressBuilder withZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public AdressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AdressBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public Adress build() {
        return new Adress(id, street, zipCode, city, country);
    }
}
