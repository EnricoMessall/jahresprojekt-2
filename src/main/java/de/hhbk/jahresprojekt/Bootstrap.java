package de.hhbk.jahresprojekt;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.*;
import de.hhbk.jahresprojekt.database.repositories.*;
import de.hhbk.jahresprojekt.model.Address;
import de.hhbk.jahresprojekt.model.BankAccount;
import de.hhbk.jahresprojekt.model.Item;
import de.hhbk.jahresprojekt.model.RoleType;
import de.hhbk.jahresprojekt.model.builder.RoleBuilder;
import de.hhbk.jahresprojekt.views.ViewManager;
import de.hhbk.jahresprojekt.views.components.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Enrico Messall
 */
public class Bootstrap extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        registerRepositories();
        registerDialogs();

        ViewManager.getInstance().setPrimaryStage(primaryStage);
        ViewManager.getInstance().activateScene(ViewManager.getInstance().getSceneLoginview());
    }

    private void registerDialogs() throws Exception{
        DialogContainer.registerDialog(Item.class, ItemDialog.class);
        DialogContainer.registerDialog(Address.class, AddressDialog.class);
        DialogContainer.registerDialog(BankAccount.class, BankAccountDialog.class);
    }

    private void registerRepositories() throws Exception {
        RepositoryContainer.registerRepository(UserRepository.class);
        RepositoryContainer.registerRepository(TenantRepository.class);
        RepositoryContainer.registerRepository(RentalObjectRepository.class);
        RepositoryContainer.registerRepository(InvoiceRepository.class);
        RepositoryContainer.registerRepository(AddressRepository.class);
        RepositoryContainer.registerRepository(DocumentRepository.class);
        RepositoryContainer.registerRepository(PaymentReceivedRepository.class);
        RepositoryContainer.registerRepository(FileRepository.class);
        RepositoryContainer.registerRepository(RoleRepository.class);
        RepositoryContainer.registerRepository(ItemRepository.class);
        RepositoryContainer.registerRepository(BankAccountRepository.class);

        RoleRepository roleRepository = RepositoryContainer.get(RoleRepository.class);
        roleRepository.findAll().forEach(roleRepository::delete);
        roleRepository.save(RoleBuilder.aRole().withRoleType(RoleType.ADMIN).build());
        roleRepository.save(RoleBuilder.aRole().withRoleType(RoleType.DEFAULT).build());
    }

    private void openModuleAndCloseNav(Workbench workbench, WorkbenchModule workbenchModule){
        workbench.hideNavigationDrawer();
        workbench.openModule(workbenchModule);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
