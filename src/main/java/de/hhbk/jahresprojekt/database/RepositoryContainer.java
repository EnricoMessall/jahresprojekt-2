package de.hhbk.jahresprojekt.database;

import de.hhbk.jahresprojekt.views.components.Dialog;

import java.util.HashMap;
import java.util.Map;

public class RepositoryContainer {

    private static final Map<Class<?>, Object> repositories = new HashMap<>();

    public static <T, I extends Repository<T>> I get(Class<T> theClass){
        return (I) repositories.getOrDefault(theClass, null);
    }

    public static void registerRepository(Class<?> theClass, Class<? extends Repository<?>> repository) throws Exception {
        repositories.put(theClass, repository.getDeclaredConstructor().newInstance());
    }
}
