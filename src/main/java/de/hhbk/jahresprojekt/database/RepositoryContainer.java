package de.hhbk.jahresprojekt.database;

import java.util.HashMap;
import java.util.Map;

public class RepositoryContainer {

    private static final Map<Class<? extends Repository<?>>, Object> repositories = new HashMap<>();

    public static <T extends Repository<?>> T get(Class<T> repository){
        return repository.cast(repositories.getOrDefault(repository, null));
    }

    public static void registerRepository(Class<? extends Repository<?>> theClass) throws Exception {
        repositories.put(theClass, theClass.getDeclaredConstructor().newInstance());
    }
}
