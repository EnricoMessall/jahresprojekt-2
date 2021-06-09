package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.RentalObjectRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

/**
 * @author Frederick Hafemann
 * @author Enrico Messall
 */
public class RentalObjectModule extends WorkbenchModule {

    private final BaseTableView<RentalObject> baseTableView;

    public RentalObjectModule() {
        super("Objektverwaltung", MaterialDesignIcon.HOME);
        baseTableView = new BaseTableView<>(RentalObject.class,
                RepositoryContainer.get(RentalObjectRepository.class),
                (data, query) -> data.getAdress().getStreet().contains(query),
                "Nummer", "Adresse", "Typ");
        baseTableView.getTable().setCellFactory("Adresse", a -> a.getAdress() != null?a.getAdress().toString():"");
    }

    @Override
    public Node activate() {
        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        baseTableView.getTable().refresh();
        return baseTableView;
    }
}
