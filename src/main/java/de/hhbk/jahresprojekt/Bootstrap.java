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
import de.hhbk.jahresprojekt.database.repositories.FileRepository;
import de.hhbk.jahresprojekt.database.repositories.PaymentReceivedRepository;
import de.hhbk.jahresprojekt.database.repositories.UserRepository;
import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.model.File;
import de.hhbk.jahresprojekt.model.builder.FileBuilder;
import de.hhbk.jahresprojekt.views.modules.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.util.Date;

/**
 * @author Enrico Messall
 */
public class Bootstrap extends Application {

    private static final MenuItem[] ITEMS = {
            new MenuItem("Dashboard", new MaterialDesignIconView(MaterialDesignIcon.VIEW_DASHBOARD)),
            new MenuItem("Objektverwaltung", new MaterialDesignIconView(MaterialDesignIcon.HOME)),
            new MenuItem("Mieterverwaltung", new MaterialDesignIconView(MaterialDesignIcon.HUMAN)),
            new MenuItem("Rechnungen", new MaterialDesignIconView(MaterialDesignIcon.BOOK)),
            new MenuItem("Userverwaltung", new MaterialDesignIconView(MaterialDesignIcon.HUMAN)),
            new MenuItem("Dokumente", new MaterialDesignIconView(MaterialDesignIcon.FILE_DOCUMENT)),
            new MenuItem("Zahlungseingänge", new MaterialDesignIconView(MaterialDesignIcon.PANDA))
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Immobilien Verwaltung");

        registerRepositories();

        RentalObjectModule rentalObjectModule = new RentalObjectModule();
        TenantModule tenantModule = new TenantModule();
        InvoiceModule invoiceModule = new InvoiceModule();
        UserModule userModule = new UserModule();
        DocumentModule documentModule = new DocumentModule();
        PaymentReceivedModule paymentReceivedModule = new PaymentReceivedModule();
        Workbench workbench = Workbench
                .builder(rentalObjectModule, tenantModule, invoiceModule, userModule, documentModule, paymentReceivedModule)
                .toolbarRight(new ToolbarItem())
                .navigationDrawerItems(ITEMS).build();

        ITEMS[0].setOnAction(action -> {
            workbench.hideNavigationDrawer();
            workbench.openAddModulePage();
        });
        ITEMS[1].setOnAction(action -> openModuleAndCloseNav(workbench, rentalObjectModule));
        ITEMS[2].setOnAction(action -> openModuleAndCloseNav(workbench, tenantModule));
        ITEMS[3].setOnAction(action -> openModuleAndCloseNav(workbench, invoiceModule));
        ITEMS[4].setOnAction(action -> openModuleAndCloseNav(workbench, userModule));
        ITEMS[5].setOnAction(action -> openModuleAndCloseNav(workbench, documentModule));
        ITEMS[6].setOnAction(action -> openModuleAndCloseNav(workbench, paymentReceivedModule));

        Scene myScene = new Scene(workbench);
        primaryStage.setScene(myScene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(650);
        primaryStage.show();
        primaryStage.centerOnScreen();
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
    }

    private void openModuleAndCloseNav(Workbench workbench, WorkbenchModule workbenchModule){
        workbench.hideNavigationDrawer();
        workbench.openModule(workbenchModule);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
