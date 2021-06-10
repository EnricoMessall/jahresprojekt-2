package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.HibernateManager;
import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.BankAccount;
import de.hhbk.jahresprojekt.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class BankAccountRepository extends Repository<BankAccount> {
    public BankAccountRepository() {
        super(BankAccount.class);
    }
}
