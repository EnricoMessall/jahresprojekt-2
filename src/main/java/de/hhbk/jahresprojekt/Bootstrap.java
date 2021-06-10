package de.hhbk.jahresprojekt;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.*;
import de.hhbk.jahresprojekt.database.repositories.*;
import de.hhbk.jahresprojekt.model.*;
import de.hhbk.jahresprojekt.model.builder.RoleBuilder;
import de.hhbk.jahresprojekt.views.ViewManager;
import de.hhbk.jahresprojekt.views.components.*;
import de.hhbk.jahresprojekt.views.components.Error;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Enrico Messall
 */
public class Bootstrap extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            registerRepositories();
            registerDialogs();

            ViewManager.getInstance().setPrimaryStage(primaryStage);
            ViewManager.getInstance().activateScene(ViewManager.getInstance().getSceneLoginview());
        } catch (Throwable e){
            new Error(e.getMessage());
        }
    }

    private void registerDialogs() throws Exception{
        DialogContainer.registerDialog(Item.class, ItemDialog.class);
        DialogContainer.registerDialog(Address.class, AddressDialog.class);
        DialogContainer.registerDialog(RentalType.class, RentalTypeDialog.class);
        DialogContainer.registerDialog(RoleType.class, RoleTypeDialog.class);
        DialogContainer.registerDialog(BankAccount.class, BankAccountDialog.class);
    }

    private void registerRepositories() throws Exception {
        RepositoryContainer.registerRepository(User.class, UserRepository.class);
        RepositoryContainer.registerRepository(Tenant.class, TenantRepository.class);
        RepositoryContainer.registerRepository(RentalObject.class, RentalObjectRepository.class);
        RepositoryContainer.registerRepository(Invoice.class, InvoiceRepository.class);
        RepositoryContainer.registerRepository(Address.class, AddressRepository.class);
        RepositoryContainer.registerRepository(Document.class, DocumentRepository.class);
        RepositoryContainer.registerRepository(PaymentReceived.class, PaymentReceivedRepository.class);
        RepositoryContainer.registerRepository(File.class, FileRepository.class);
        RepositoryContainer.registerRepository(Role.class, RoleRepository.class);
        RepositoryContainer.registerRepository(Item.class, ItemRepository.class);
        RepositoryContainer.registerRepository(BankAccount.class, BankAccountRepository.class);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
