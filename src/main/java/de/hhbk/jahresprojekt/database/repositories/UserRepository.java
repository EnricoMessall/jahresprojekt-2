package de.hhbk.jahresprojekt.database;

import de.hhbk.jahresprojekt.model.User;

public class UserRepository extends Repository<User> {

    public UserRepository() {
        super(User.class);
    }
}
