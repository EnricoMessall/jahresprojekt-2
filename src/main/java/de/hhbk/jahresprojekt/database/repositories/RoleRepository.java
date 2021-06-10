package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.HibernateManager;
import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.Address;
import de.hhbk.jahresprojekt.model.Role;
import de.hhbk.jahresprojekt.model.RoleType;
import de.hhbk.jahresprojekt.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository<Role> {
    public RoleRepository() {
        super(Role.class);
    }
}
