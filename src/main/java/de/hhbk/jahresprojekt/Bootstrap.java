package de.hhbk.jahresprojekt;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.dlsc.workbenchfx.view.controls.ToolbarItem;
import de.hhbk.jahresprojekt.views.modules.InvoiceModule;
import de.hhbk.jahresprojekt.views.modules.RentalObjectModule;
import de.hhbk.jahresprojekt.views.modules.TenantModule;
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

    private static final MenuItem[] ITEMS = {
            new MenuItem("Dashboard", new MaterialDesignIconView(MaterialDesignIcon.VIEW_DASHBOARD)),
            new MenuItem("Objektverwaltung", new MaterialDesignIconView(MaterialDesignIcon.HOME)),
            new MenuItem("Mieterverwaltung", new MaterialDesignIconView(MaterialDesignIcon.HUMAN)),
            new MenuItem("Rechnungen", new MaterialDesignIconView(MaterialDesignIcon.BOOK))
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Immobilien Verwaltung");

        RentalObjectModule rentalObjectModule = new RentalObjectModule();
        TenantModule tenantModule = new TenantModule();
        InvoiceModule invoiceModule = new InvoiceModule();
        Workbench workbench = Workbench
                .builder(rentalObjectModule, tenantModule, invoiceModule)
                .toolbarRight(new ToolbarItem())
                .navigationDrawerItems(ITEMS).build();

        ITEMS[0].setOnAction(action -> {
            workbench.hideNavigationDrawer();
            workbench.openAddModulePage();
        });
        ITEMS[1].setOnAction(action -> openModuleAndCloseNav(workbench, rentalObjectModule));
        ITEMS[2].setOnAction(action -> openModuleAndCloseNav(workbench, tenantModule));
        ITEMS[3].setOnAction(action -> openModuleAndCloseNav(workbench, invoiceModule));

        Scene myScene = new Scene(workbench);
        primaryStage.setScene(myScene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(650);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    private void openModuleAndCloseNav(Workbench workbench, WorkbenchModule workbenchModule){
        workbench.hideNavigationDrawer();
        workbench.openModule(workbenchModule);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
