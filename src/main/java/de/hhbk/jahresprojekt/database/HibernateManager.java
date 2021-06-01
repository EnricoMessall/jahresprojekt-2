package de.hhbk.jahresprojekt.database;

import de.hhbk.jahresprojekt.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateManager {
    private static SessionFactory sessionFactory = null;

    protected static SessionFactory getSessionFactory() {
        return sessionFactory == null?buildSessionFactory():sessionFactory;
    }

    protected static void shutdown() { getSessionFactory().close(); }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "xwrqavrtyqrhgsre");
            configuration.setProperty("hibernate.connection.url", "jdbc:mariadb://127.0.0.1:3306/immo");
            configuration.setProperty("hibernate.connection.driver_class", "org.mariadb.jdbc.Driver");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
//            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.use_sql_comments", "true");

            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(BankAccount.class);
            configuration.addAnnotatedClass(Document.class);
            configuration.addAnnotatedClass(File.class);
            configuration.addAnnotatedClass(Invoice.class);
            configuration.addAnnotatedClass(Item.class);
            configuration.addAnnotatedClass(PaymentReceived.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(RentalObject.class);
            configuration.addAnnotatedClass(Role.class);
            configuration.addAnnotatedClass(Tenant.class);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
