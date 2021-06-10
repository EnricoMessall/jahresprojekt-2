package de.hhbk.jahresprojekt;

import com.google.common.base.Optional;
import de.hhbk.jahresprojekt.database.HibernateManager;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.AddressRepository;
import de.hhbk.jahresprojekt.database.repositories.BankAccountRepository;
import de.hhbk.jahresprojekt.database.repositories.UserRepository;
import de.hhbk.jahresprojekt.model.*;
import de.hhbk.jahresprojekt.model.builder.AdressBuilder;
import de.hhbk.jahresprojekt.model.builder.BankAccountBuilder;
import de.hhbk.jahresprojekt.model.builder.UserBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.*;

import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

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

        RepositoryContainer.registerRepository(BankAccount.class, BankAccountRepository.class);
        RepositoryContainer.registerRepository(User.class, UserRepository.class);
        RepositoryContainer.registerRepository(Address.class, AddressRepository.class );


    }

    @Test
    public void testGetBankAccountById() {
        BankAccountRepository repository = RepositoryContainer.get(BankAccount.class);
        BankAccount bankAccount = BankAccountBuilder.aBankAccount().withAccountOwner("Hans").withBic("1234567")
                .withCreditInstitution("Comdiret").withIban("DE00 1234 1234 1234 1234").build();
        bankAccount = repository.save(bankAccount);

        Optional<BankAccount> result = repository.findById(bankAccount.getId());

        assertFalse(result.isEmpty());
        assertEquals(result.get().getAccountOwner(), bankAccount.getAccountOwner());
        assertEquals(result.get().getId(), bankAccount.getId());
        assertEquals(result.get().getIban(), bankAccount.getIban());


    }

    @Test
    public void testSaveBankAccount() {
        BankAccountRepository repository = RepositoryContainer.get(BankAccount.class);
        BankAccount bankAccount = BankAccountBuilder.aBankAccount().withAccountOwner("Hans").withBic("1234567")
                .withCreditInstitution("Comdiret").withIban("DE00 1234 1234 1234 1234").build();
        bankAccount = repository.save(bankAccount);

        bankAccount.setCreditInstitution("Volksbank");
        repository.save(bankAccount);

        Optional<BankAccount> result = repository.findById(bankAccount.getId());

        assertFalse(result.isEmpty());
        assertEquals(result.get().getCreditInstitution(), "Volksbank");

    }

    @Test
    public void testDeleteBankAccount() {
        BankAccountRepository repository = RepositoryContainer.get(BankAccount.class);
        BankAccount bankAccount = BankAccountBuilder.aBankAccount().withAccountOwner("Hans").withBic("1234567")
                .withCreditInstitution("Comdiret").withIban("DE00 1234 1234 1234 1234").build();
        bankAccount = repository.save(bankAccount);

        Optional<BankAccount> result = repository.findById(bankAccount.getId());

        assertFalse(result.isEmpty());

        repository.delete(bankAccount);

        Optional<BankAccount> result2 = repository.findById(bankAccount.getId());

        assertTrue(result2.isEmpty());

    }

    @Test
    public void testGetUserById() {
        UserRepository repository = RepositoryContainer.get(User.class);
        User user = UserBuilder.anUser().withAdress(null).withEmail("mueller@gmail.com")
                .withFirstName("Hans").withLastName("Müller").withPassword("pw").withPhoneNumberLandline("+49")
                .withUsername("hmue").build();
        user = repository.save(user);

        Optional<User> result = repository.findById(user.getId());

        assertFalse(result.isEmpty());
        assertEquals(result.get().getUsername(), user.getUsername());
        assertEquals(result.get().getId(), user.getId());
        assertEquals(result.get().getEmail(), user.getEmail());


    }

    @Test
    public void testSaveUser() {
        UserRepository repository = RepositoryContainer.get(User.class);
        User user = UserBuilder.anUser().withAdress(null).withEmail("mueller@gmail.com")
                .withFirstName("Hans").withLastName("Müller").withPassword("pw").withPhoneNumberLandline("+49")
                .withUsername("hmue").build();
        repository.save(user);

        user.setFirstName("Max");
        user = repository.save(user);

        Optional<User> result = repository.findById(user.getId());

        assertFalse(result.isEmpty());
        assertEquals(result.get().getFirstName(), "Max");

    }

    @Test
    public void testDeleteUser() {
        UserRepository repository = RepositoryContainer.get(User.class);
        User user = UserBuilder.anUser().withAdress(null).withEmail("mueller@gmail.com")
                .withFirstName("Hans").withLastName("Müller").withPassword("pw").withPhoneNumberLandline("+49")
                .withUsername("hmue").build();
        user = repository.save(user);

        Optional<User> result = repository.findById(user.getId());

        assertFalse(result.isEmpty());

        repository.delete(user);

        Optional<User> result2 = repository.findById(user.getId());

        assertTrue(result2.isEmpty());

    }

    @Test
    public void testGetAddressById (){
        AddressRepository repository = RepositoryContainer.get(Address.class);
        Address address = AdressBuilder.anAdress().withStreet("Hans-Mueller-Strasse 4").withZipCode("42555")
                .withCity("Velbert").withCountry("Germany").build();
        repository.save(address);

        Optional<Address> result = repository.findById(address.getId());

        assertFalse(result.isEmpty());
        assertEquals(result.get().getId(), address.getId());

    }

    @Test
    public void testSaveAddress() {
        AddressRepository repository = RepositoryContainer.get(Address.class);
        Address address = AdressBuilder.anAdress().withStreet("Hans-Mueller-Strasse 4").withZipCode("42555")
                .withCity("Velbert").withCountry("Germany").build();
        repository.save(address);

        address.setZipCode("42553");
        address = repository.save(address);

        Optional<Address> result = repository.findById(address.getId());

        assertFalse(result.isEmpty());
        assertEquals(result.get().getZipCode(), "42553");

    }

    @Test
    public void testDeleteAddress() {
        AddressRepository repository = RepositoryContainer.get(Address.class);
        Address address = AdressBuilder.anAdress().withStreet("Hans-Mueller-Strasse 4").withZipCode("42555")
                .withCity("Velbert").withCountry("Germany").build();
        address = repository.save(address);

        Optional<Address> result = repository.findById(address.getId());

        assertFalse(result.isEmpty());

        repository.delete(address);

        Optional<Address> result2 = repository.findById(address.getId());

        assertTrue(result2.isEmpty());

    }

    @Test
    public void testFindByUsername (){
        UserRepository repository = RepositoryContainer.get(User.class);
        User user = UserBuilder.anUser().withAdress(null).withEmail("mueller@gmail.com")
                .withFirstName("Hans").withLastName("Müller").withPassword("pw").withPhoneNumberLandline("+49")
                .withUsername("hmue").build();
        repository.save(user);

        User result = repository.findByUsername("hmue");

        assertFalse(result.getUsername().isEmpty());

    }

    @AfterAll
    public static void after() {
        HibernateManager.getSessionFactory().close();
    }
}
