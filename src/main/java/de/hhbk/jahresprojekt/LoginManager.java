package de.hhbk.jahresprojekt;

import de.hhbk.jahresprojekt.database.repositories.UserRepository;
import de.hhbk.jahresprojekt.model.RoleType;
import de.hhbk.jahresprojekt.model.User;

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
        return currentUser == null ? false : currentUser.getRole().getRoleType() == RoleType.ADMIN;
    }

}
