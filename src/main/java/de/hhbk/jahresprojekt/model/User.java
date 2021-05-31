package de.hhbk.jahresprojekt.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class User extends Person{
    private String username;
    private String password;
    @ManyToOne
    private Role role;

    public User() {
    }

    public User(Long id, String title, String firstName, String lastName, String phoneNumberMobile, String phoneNumberLandline, String email, Address address, String username, String password, Role role) {
        super(id, title, firstName, lastName, phoneNumberMobile, phoneNumberLandline, email, address);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
