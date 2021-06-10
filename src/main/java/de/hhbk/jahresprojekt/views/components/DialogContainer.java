package de.hhbk.jahresprojekt.views.components;

import de.hhbk.jahresprojekt.model.RentalType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Enrico Messall
 */
public class DialogContainer {


    private static final Map<Class<?>, Object> dialogs = new HashMap<>();

    public static <T, I extends Dialog<T>> I get(Class<T> theClass){
        Object object = dialogs.get(theClass);
        if(object != null) return (I) object;
        return (I) new SelectDialog<>(theClass);
    }

    public static <T> void registerDialog(Class<T> theClass, Class<? extends Dialog<T>> theDialog) throws Exception {
        dialogs.put(theClass, theDialog.getDeclaredConstructor().newInstance());
    }
}
