package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.HibernateManager;
import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Jonas Rehrmann
 */
public class UserRepository extends Repository {
    public UserRepository() {
        super(User.class);
    }

    /**
     * Gibt einen Benutzer anhand des Usernames zurück.
     * Sollten mehrere gefunden werden der erste aus der Liste
     * Sollte keiner gefunden werden wird null zurückgegeben
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        User user;
        SessionFactory sessionFactory = HibernateManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String queryString = "SELECT * FROM User WHERE username = :param1 ";
        Query query = session.createSQLQuery(queryString).addEntity(User.class);
        query.setParameter("param1",username);
        List<User> result = query.list();

        user = result.isEmpty() ? null : result.get(0);

        session.getTransaction().commit();
        session.close();

        return user;
    }
}
