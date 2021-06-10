package de.hhbk.jahresprojekt;

import de.hhbk.jahresprojekt.database.HibernateManager;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.UserRepository;
import de.hhbk.jahresprojekt.model.*;
import de.hhbk.jahresprojekt.model.builder.UserBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.*;

import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class DatabaseTesting extends Assertions {

    @BeforeAll
    public static void before() throws Exception {
        // setup the session factory
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect",
                "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class",
                "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:immo");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");

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
        HibernateManager.setSessionFactory(configuration.buildSessionFactory(serviceRegistry));


        RepositoryContainer.registerRepository(User.class, UserRepository.class);


    }

    @Test
    public void testGetById() {
        UserRepository userRepository = RepositoryContainer.get(User.class);
        User user = UserBuilder.anUser().withEmail("mueller@gmail.com")
                .withFirstName("Hans").withLastName("Müller").withPassword("pw").withPhoneNumberLandline("+49")
                .withUsername("hmue").build();
        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        Optional<User> result = userRepository.findById(user.getId());

        assertFalse(result.isEmpty());
        assertEquals(result.get().getUsername(), user.getUsername());
        assertEquals(result.get().getId(), user.getId());
        assertEquals(result.get().getEmail(), user.getEmail());


    }

    @Test
    public void testSaveUser() {
        UserRepository userRepository = RepositoryContainer.get(User.class);
        User user = UserBuilder.anUser().withEmail("mueller@gmail.com")
                .withFirstName("Hans").withLastName("Müller").withPassword("pw").withPhoneNumberLandline("+49")
                .withUsername("hmue").build();
        userRepository.save(user);

        user.setFirstName("Max");
        userRepository.save(user);

        Optional<User> result = userRepository.findById(user.getId());

        assertFalse(result.isEmpty());
        assertEquals(result.get().getFirstName(), "Max");

    }

    @Test
    public void testDeleteUser() {
        UserRepository userRepository = RepositoryContainer.get(User.class);
        User user = UserBuilder.anUser().withEmail("mueller@gmail.com")
                .withFirstName("Hans").withLastName("Müller").withPassword("pw").withPhoneNumberLandline("+49")
                .withUsername("hmue").build();
        userRepository.save(user);

        Optional<User> result = userRepository.findById(user.getId());

        assertFalse(result.isEmpty());

        userRepository.delete(user);

        Optional<User> result2 = userRepository.findById(user.getId());

        assertTrue(result2.isEmpty());

    }

    @Test
    public void testFindByUsername (){
        UserRepository userRepository = RepositoryContainer.get(User.class);
        User user = UserBuilder.anUser().withEmail("mueller@gmail.com")
                .withFirstName("Hans").withLastName("Müller").withPassword("pw").withPhoneNumberLandline("+49")
                .withUsername("hmue").build();
        userRepository.save(user);

       User result = userRepository.findByUsername("hmue");

        assertFalse(result.getUsername().isEmpty());





    }

    @AfterAll
    public static void after() {
        HibernateManager.getSessionFactory().close();
    }
}
