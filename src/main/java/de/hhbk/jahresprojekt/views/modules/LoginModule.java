package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.views.modules.view.LoginView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
/**
 * @author Jonas Rehrmann
 */
public class LoginModule extends WorkbenchModule {

    private final LoginView view;

    public LoginModule() {
        super("Login", MaterialDesignIcon.LOGIN);

        view = new LoginView();
    }


    @Override
    public Node activate() {
        return view;
    }
}
