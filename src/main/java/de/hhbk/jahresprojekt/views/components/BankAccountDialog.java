package de.hhbk.jahresprojekt.views.components;

import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.BankAccountRepository;
import de.hhbk.jahresprojekt.model.BankAccount;
import de.hhbk.jahresprojekt.model.builder.BankAccountBuilder;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * @author Frederick Hafemann
 */
public class BankAccountDialog extends Dialog<BankAccount>{
    VBox vBox;
    TextField iban, bic, accountOwner, creditInstiture;

    public BankAccountDialog() {
        super(BankAccount.class);
    }

    @Override
    Node initView() {
        vBox = new VBox();
        iban = new TextField();
        bic = new TextField();
        accountOwner = new TextField();
        creditInstiture = new TextField();

        vBox.getChildren().add(new Label("IBAN"));
        vBox.getChildren().add(iban);
        vBox.getChildren().add(new Label("BIC"));
        vBox.getChildren().add(bic);
        vBox.getChildren().add(new Label("Inhaber"));
        vBox.getChildren().add(accountOwner);
        vBox.getChildren().add(new Label("Kredit Institut"));
        vBox.getChildren().add(creditInstiture);
        return vBox;
    }

    @Override
    public void copyFrom(BankAccount object) {
        if(object == null) return;
        iban.setText(object.getIban());
        bic.setText(object.getIban());
        accountOwner.setText(object.getAccountOwner());
        creditInstiture.setText(object.getCreditInstitution());
    }

    @Override
    BankAccount getChangedObject() {
        BankAccount bankAccount = BankAccountBuilder.aBankAccount().withIban(iban.getText()).withBic(bic.getText()).withAccountOwner(accountOwner.getText()).withCreditInstitution(creditInstiture.getText()).build();
        RepositoryContainer.get(BankAccount.class).save(bankAccount);
        return bankAccount;
    }
}
