package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.Address;
import de.hhbk.jahresprojekt.model.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository<Role> {
    public RoleRepository() {
        super(Role.class);
    }
}
