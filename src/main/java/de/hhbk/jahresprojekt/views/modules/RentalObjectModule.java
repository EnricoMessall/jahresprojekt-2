package de.hhbk.jahresprojekt.views.modules;

import de.hhbk.jahresprojekt.database.RentalObjectRepository;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.views.modules.autofetch.AutoFetchWorkbenchModule;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

public class RentalObjectModule extends AutoFetchWorkbenchModule<RentalObject> {

    private final BaseTableView<RentalObject> baseTableView;

    public RentalObjectModule() {
        super("Objektverwaltung", MaterialDesignIcon.HOME);
        baseTableView = new BaseTableView<>(RentalObject.class,
                (data, query) -> data.getAdress().getStreet().contains(query),
                "Nummer", "Adresse", "Typ");

        setRepository(new RentalObjectRepository());
        setOnFetchedListener(baseTableView::setData);
        refresh();
    }

    @Override
    public Node activate() {
        return baseTableView;
    }
}
