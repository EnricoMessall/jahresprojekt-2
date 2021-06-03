package de.hhbk.jahresprojekt.model;

import de.hhbk.jahresprojekt.views.annotations.TableField;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
public class User extends Person{
    @TableField(label = "Name")
    private String username;
    @TableField(label = "Passwort")
    private String password;
    @ManyToOne
    private Role role;

    public User() {
    }

    public User(Long id, String title, String firstName, String lastName, String phoneNumberMobile, String phoneNumberLandline, String email, Address address, String username, String password, Role role) {
        super(id, title, firstName, lastName, phoneNumberMobile, phoneNumberLandline, email, address);
        this.username = username;
        this.password = password;
        setRole(role);
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
