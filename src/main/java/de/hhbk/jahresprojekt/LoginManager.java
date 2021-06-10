package de.hhbk.jahresprojekt;

import de.hhbk.jahresprojekt.database.repositories.UserRepository;
import de.hhbk.jahresprojekt.model.RoleType;
import de.hhbk.jahresprojekt.model.User;
import de.hhbk.jahresprojekt.model.builder.RoleBuilder;
import de.hhbk.jahresprojekt.model.builder.UserBuilder;

/**
 * @author Jonas Rehrmann
 */
public class LoginManager {
    private static LoginManager instance;
    private User currentUser;

    private LoginManager() {}

    public static LoginManager getInstance() {
        if(instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    /**
     *
     * @param username
     * @param password
     * @return false wennn die anmeldung nicht funktioniert zB aufgrund falscher Daten, sonst true
     */
    public boolean login(String username, String password) {
        if(username.equals("Admin") && password.equals("admin")) {
            currentUser = UserBuilder.anUser().withFirstName("Admin").withLastName("Admin").withUsername("Admin")
                    .withPassword("Admin").withRole(RoleBuilder.aRole().withRoleType(RoleType.ADMIN).build()).build();
            return true;
        }

        UserRepository db = new UserRepository();
        User user = db.findByUsername(username);

        if(user == null) {
            return false;
        }

        if(!user.getPassword().equals(password)) {
            return false;
        }

        currentUser = user;

        return true;
    }

    /**
     * Setzt den aktuellen Benutzer wieder auf null
     */
    public void logout() {
        currentUser = null;
    }

    /**
     *
     * @return aktueller Benutzer
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * @return true wenn der angemeldete Benutzer ein Admin ist
     */
    public boolean currentUserIsAdmin() {
        return currentUser != null && currentUser.getRole().getRoleType() == RoleType.ADMIN;
    }

}
