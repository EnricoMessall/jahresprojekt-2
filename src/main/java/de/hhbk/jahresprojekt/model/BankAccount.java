package de.hhbk.jahresprojekt.model;

public class BankAccount {
    private Long id;
    private String iban;
    private String bic;
    private String accountOwner;
    private String creditInstitution;

    public BankAccount() {
    }

    public BankAccount(Long id, String iban, String bic, String accountOwner, String creditInstitution) {
        this.id = id;
        this.iban = iban;
        this.bic = bic;
        this.accountOwner = accountOwner;
        this.creditInstitution = creditInstitution;
    }

    public Long getId() {
        return id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getCreditInstitution() {
        return creditInstitution;
    }

    public void setCreditInstitution(String creditInstitution) {
        this.creditInstitution = creditInstitution;
    }
}
