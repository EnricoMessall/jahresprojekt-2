package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.Adress;
import de.hhbk.jahresprojekt.model.Role;
import de.hhbk.jahresprojekt.model.User;

public final class UserBuilder {
    private int id;
    private String title;
    private String firstName;
    private String lastName;
    private String phoneNumberMobile;
    private String phoneNumberLandline;
    private String email;
    private Adress adress;
    private String username;
    private String password;
    private Role role;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withPhoneNumberMobile(String phoneNumberMobile) {
        this.phoneNumberMobile = phoneNumberMobile;
        return this;
    }

    public UserBuilder withPhoneNumberLandline(String phoneNumberLandline) {
        this.phoneNumberLandline = phoneNumberLandline;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withAdress(Adress adress) {
        this.adress = adress;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRole(Role role) {
        this.role = role;
        return this;
    }

    public User build() {
        return new User(id, title, firstName, lastName, phoneNumberMobile, phoneNumberLandline, email, adress, username, password, role);
    }
}
