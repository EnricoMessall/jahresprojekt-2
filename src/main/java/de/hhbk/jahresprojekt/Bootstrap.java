package de.hhbk.jahresprojekt;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.dlsc.workbenchfx.view.controls.ToolbarItem;
import de.hhbk.jahresprojekt.database.*;
import de.hhbk.jahresprojekt.database.AddressRepository;
import de.hhbk.jahresprojekt.database.InvoiceRepository;
import de.hhbk.jahresprojekt.database.RentalObjectRepository;
import de.hhbk.jahresprojekt.database.TenantRepository;
import de.hhbk.jahresprojekt.database.repositories.DocumentRepository;
import de.hhbk.jahresprojekt.database.repositories.PaymentReceivedRepository;
import de.hhbk.jahresprojekt.database.repositories.UserRepository;
import de.hhbk.jahresprojekt.views.ViewManager;
import de.hhbk.jahresprojekt.views.modules.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

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
        RepositoryContainer.registerRepository(de.hhbk.jahresprojekt.database.AddressRepository.class);
        RepositoryContainer.registerRepository(DocumentRepository.class);
        RepositoryContainer.registerRepository(PaymentReceivedRepository.class);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
