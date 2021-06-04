package de.hhbk.jahresprojekt.views.modules.autofetch;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;

import java.util.List;

public abstract class AutoFetchWorkbenchModule<T> extends WorkbenchModule {
    private Repository<T> repository;
    private OnFetchedListener onFetchedListener;

    public AutoFetchWorkbenchModule(String name, MaterialDesignIcon icon) {
        super(name, icon);
    }

    public void setRepository(Repository<T> repository){
        this.repository = repository;
    }

    public void setOnFetchedListener(OnFetchedListener onFetchedListener){
        this.onFetchedListener = onFetchedListener;
    }

    public void refresh(){
        List<T> list = RepositoryContainer.get(repository.getClass()).findAll();
        onFetchedListener.run(list);
        System.out.println("refresh");
    }
}
