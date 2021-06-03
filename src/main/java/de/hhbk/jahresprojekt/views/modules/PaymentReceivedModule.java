package de.hhbk.jahresprojekt.views.modules;

import de.hhbk.jahresprojekt.database.repositories.UserRepository;
import de.hhbk.jahresprojekt.model.User;
import de.hhbk.jahresprojekt.views.modules.autofetch.AutoFetchWorkbenchModule;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

public class UserModule extends AutoFetchWorkbenchModule<User> {

    private final BaseTableView<User> baseTableView;

    public UserModule() {
        super("Userverwaltung", MaterialDesignIcon.HUMAN);
        baseTableView = new BaseTableView<>(User.class,
                (data, query) -> data.getUsername().contains(query));

        setRepository(new UserRepository());
        setOnFetchedListener(baseTableView::setData);
        refresh();
    }

    @Override
    public Node activate() {
        return baseTableView;
    }
}
