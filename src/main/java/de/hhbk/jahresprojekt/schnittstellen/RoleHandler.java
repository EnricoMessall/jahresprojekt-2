package de.hhbk.jahresprojekt.schnittstellen;

import de.hhbk.jahresprojekt.model.Role;
import de.hhbk.jahresprojekt.model.User;

/**
 * @author Frederik Hafemann
 */
public interface RoleHandler {
    boolean authenticate(User user, Role role);
}
