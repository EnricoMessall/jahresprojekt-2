package de.hhbk.jahresprojekt.database;

import de.hhbk.jahresprojekt.model.User;

public class DaoManager {
    public Repository<User,Integer> getUserDao(){
        return new Repository<User, Integer>(User.class);
    }
}
