package de.hhbk.jahresprojekt.views.components;

import java.util.HashMap;
import java.util.Map;

public class DialogContainer {


    private static final Map<Class<?>, Object> dialogs = new HashMap<>();

    public static <T, I extends Dialog<T>> I get(Class<T> theClass){
        return (I) dialogs.getOrDefault(theClass, new SelectDialog<>(theClass));
    }

    public static <T> void registerDialog(Class<T> theClass, Class<? extends Dialog<T>> theDialog) throws Exception {
        dialogs.put(theClass, theClass.getDeclaredConstructor().newInstance());
    }
}
