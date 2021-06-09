package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.UserRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.model.User;
import de.hhbk.jahresprojekt.views.components.DetailDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.FetchNotifier;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

/**
 * @author Frederick Hafemann
 */
public class UserModule extends WorkbenchModule {

    private final BaseTableView<User> baseTableView;

    public UserModule() {
        super("Userverwaltung", MaterialDesignIcon.HUMAN);
        baseTableView = new BaseTableView<>(User.class,
                RepositoryContainer.get(UserRepository.class),
                (data, query) -> data.getUsername().contains(query));
    }

    @Override
    public Node activate() {
        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        baseTableView.getTable().refresh();
        return baseTableView;
    }
}
