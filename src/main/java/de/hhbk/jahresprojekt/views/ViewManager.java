package de.hhbk.jahresprojekt.views;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.dlsc.workbenchfx.view.controls.ToolbarItem;
import de.hhbk.jahresprojekt.LoginManager;
import de.hhbk.jahresprojekt.database.*;
import de.hhbk.jahresprojekt.database.InvoiceRepository;
import de.hhbk.jahresprojekt.database.RentalObjectRepository;
import de.hhbk.jahresprojekt.database.TenantRepository;
import de.hhbk.jahresprojekt.database.repositories.DocumentRepository;
import de.hhbk.jahresprojekt.database.repositories.PaymentReceivedRepository;
import de.hhbk.jahresprojekt.database.repositories.UserRepository;
import de.hhbk.jahresprojekt.views.modules.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * @author Jonas Rehrmann
 */
public class ViewManager {
    private static ViewManager instance;

    private Stage primaryStage;

    private static final MenuItem[] ITEMS = {
            new MenuItem("Dashboard", new MaterialDesignIconView(MaterialDesignIcon.VIEW_DASHBOARD)),
            new MenuItem("Objektverwaltung", new MaterialDesignIconView(MaterialDesignIcon.HOME)),
            new MenuItem("Mieterverwaltung", new MaterialDesignIconView(MaterialDesignIcon.HUMAN)),
            new MenuItem("Rechnungen", new MaterialDesignIconView(MaterialDesignIcon.BOOK)),
            new MenuItem("Userverwaltung", new MaterialDesignIconView(MaterialDesignIcon.HUMAN)),
            new MenuItem("Dokumente", new MaterialDesignIconView(MaterialDesignIcon.FILE_DOCUMENT)),
            new MenuItem("Zahlungseingänge", new MaterialDesignIconView(MaterialDesignIcon.PANDA)),
            new MenuItem("Abmelden", new MaterialDesignIconView(MaterialDesignIcon.LOGOUT))
    };


    private ViewManager() {
    }

    public static ViewManager getInstance() {
        if(instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }

    public void setPrimaryStage(Stage primaryStage) {
        // TODO Unseriöser Code: verbessert die Performance beim Anmelden da der Erste Aufruf eines Module Konstruktors lange dauert evtl erster zugriff auf DB?
        RentalObjectModule rentalObjectModule = new RentalObjectModule();

        primaryStage.setTitle("Immobilien Verwaltung");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(650);
        primaryStage.centerOnScreen();
        this.primaryStage = primaryStage;
    }

    public void activateScene(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openModuleAndCloseNav(Workbench workbench, WorkbenchModule workbenchModule){
        workbench.hideNavigationDrawer();
        workbench.openModule(workbenchModule);
    }

    public Scene getSceneMainview() {
        // TODO Logik einkommentieren dass tatsächlich geprüft wird ob der aktuelle Benutzer Admin ist
        boolean isAdmin = true; //LoginManager.getInstance().currentUserIsAdmin();

        RentalObjectModule rentalObjectModule = new RentalObjectModule();
        TenantModule tenantModule = new TenantModule();
        InvoiceModule invoiceModule = new InvoiceModule();
        UserModule userModule = isAdmin ? new UserModule() : null;
        DocumentModule documentModule = new DocumentModule();
        PaymentReceivedModule paymentReceivedModule = new PaymentReceivedModule();
        Workbench workbench;
        if(isAdmin) {
            workbench = Workbench
                    .builder(rentalObjectModule, tenantModule, invoiceModule, userModule, documentModule, paymentReceivedModule)
                    .toolbarRight(new ToolbarItem())
                    .navigationDrawerItems(ITEMS).build();
        } else {
            workbench = Workbench
                    .builder(rentalObjectModule, tenantModule, invoiceModule, documentModule, paymentReceivedModule)
                    .toolbarRight(new ToolbarItem())
                    .navigationDrawerItems(ITEMS).build();
        }


        ITEMS[0].setOnAction(action -> {
            workbench.hideNavigationDrawer();
            workbench.openAddModulePage();
        });
        ITEMS[1].setOnAction(action -> openModuleAndCloseNav(workbench, rentalObjectModule));
        ITEMS[2].setOnAction(action -> openModuleAndCloseNav(workbench, tenantModule));
        ITEMS[3].setOnAction(action -> openModuleAndCloseNav(workbench, invoiceModule));
        if(isAdmin) {
            ITEMS[4].setOnAction(action -> openModuleAndCloseNav(workbench, userModule));
        } else {
            ITEMS[4].setDisable(true);
        }
        ITEMS[5].setOnAction(action -> openModuleAndCloseNav(workbench, documentModule));
        ITEMS[6].setOnAction(action -> openModuleAndCloseNav(workbench, paymentReceivedModule));
        ITEMS[7].setOnAction(action -> logout());

        return new Scene(workbench);
    }

    public Scene getSceneLoginview() {
        LoginModule loginModule = new LoginModule();
        Workbench loginWorkbench = Workbench.builder(loginModule).build();

        return new Scene(loginWorkbench);
    }

    public void logout() {
        LoginManager.getInstance().logout();
        activateScene(getSceneLoginview());
    }

}
