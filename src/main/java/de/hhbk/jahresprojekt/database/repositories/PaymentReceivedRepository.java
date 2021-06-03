package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.User;

public class UserRepository extends Repository<User> {

    public UserRepository() {
        super(User.class);
    }
}
