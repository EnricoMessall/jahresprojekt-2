package de.hhbk.jahresprojekt;

import de.hhbk.jahresprojekt.database.UserRepository;
import de.hhbk.jahresprojekt.model.Role;
import de.hhbk.jahresprojekt.model.RoleType;
import de.hhbk.jahresprojekt.model.User;
import de.hhbk.jahresprojekt.schnittstellen.RoleHandler;

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
     *
     * @return aktueller Benutzer
     */
    public User getCurrentUser() {
        return currentUser;
    }

    public boolean currentUserIsAdmin() {
        return getCurrentUser().getRole().getRoleType() == RoleType.ADMIN;
    }

}
