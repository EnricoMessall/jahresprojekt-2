package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.BankAccount;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
public final class BankAccountBuilder {
    private Long id;
    private String iban;
    private String bic;
    private String accountOwner;
    private String creditInstitution;

    private BankAccountBuilder() {
    }

    public static BankAccountBuilder aBankAccount() {
        return new BankAccountBuilder();
    }

    public BankAccountBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public BankAccountBuilder withIban(String iban) {
        this.iban = iban;
        return this;
    }

    public BankAccountBuilder withBic(String bic) {
        this.bic = bic;
        return this;
    }

    public BankAccountBuilder withAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
        return this;
    }

    public BankAccountBuilder withCreditInstitution(String creditInstitution) {
        this.creditInstitution = creditInstitution;
        return this;
    }

    public BankAccount build() {
        return new BankAccount(id, iban, bic, accountOwner, creditInstitution);
    }
}
