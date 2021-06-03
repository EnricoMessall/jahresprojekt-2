package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.model.User;
import de.hhbk.jahresprojekt.model.builder.UserBuilder;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TenantModule extends WorkbenchModule {

    private final BaseTableView<User> baseTableView;

    public TenantModule() {
        super("Mieterverwaltung", MaterialDesignIcon.HUMAN);
        baseTableView = new BaseTableView<>(User.class,
                (data, query) -> data.getUsername().contains(query));

        User user = UserBuilder.anUser().withUsername("Bob Marley").withPassword("122").build();
        User user1 = UserBuilder.anUser().withUsername("Günther Gans").withPassword("211").build();
        User user2 = UserBuilder.anUser().withUsername("Snoop Dogg").withPassword("212").build();
        User user3 = UserBuilder.anUser().withUsername("Max Mustermann").withPassword("123").build();
        User user4 = UserBuilder.anUser().withUsername("Donald Trump").withPassword("312").build();
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        baseTableView.setData(users);
    }

    @Override
    public Node activate() {
        return baseTableView;
    }
}
