package de.hhbk.jahresprojekt.model;

public class User extends Person{
    private Adress adress;
    private String username;
    private String password;
    private Role role;

    public User() {
    }

    public User(int id, String title, String firstName, String lastName, String phoneNumberMobile, String phoneNumberLandline, String email, Adress adress, String username, String password, Role role) {
        super(id, title, firstName, lastName, phoneNumberMobile, phoneNumberLandline, email);
        this.adress = adress;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
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
