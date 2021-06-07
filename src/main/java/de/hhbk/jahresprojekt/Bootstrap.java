package de.hhbk.jahresprojekt;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.dlsc.workbenchfx.view.controls.ToolbarItem;
import de.hhbk.jahresprojekt.database.*;
import de.hhbk.jahresprojekt.database.AddressRepository;
import de.hhbk.jahresprojekt.database.InvoiceRepository;
import de.hhbk.jahresprojekt.database.RentalObjectRepository;
import de.hhbk.jahresprojekt.database.TenantRepository;
import de.hhbk.jahresprojekt.database.repositories.*;
import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.model.File;
import de.hhbk.jahresprojekt.model.Role;
import de.hhbk.jahresprojekt.model.RoleType;
import de.hhbk.jahresprojekt.model.builder.FileBuilder;
import de.hhbk.jahresprojekt.model.builder.RoleBuilder;
import de.hhbk.jahresprojekt.views.ViewManager;
import de.hhbk.jahresprojekt.views.modules.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Date;

/**
 * @author Enrico Messall
 */
public class Bootstrap extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        registerRepositories();

        ViewManager.getInstance().setPrimaryStage(primaryStage);
        ViewManager.getInstance().activateScene(ViewManager.getInstance().getSceneLoginview());
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
