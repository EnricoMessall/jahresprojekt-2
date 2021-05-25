package de.hhbk.jahresprojekt.schnittstellen;

import de.hhbk.jahresprojekt.model.Role;
import de.hhbk.jahresprojekt.model.User;

public interface RoleHandler {
    boolean authenticate(User user, Role role);
}
