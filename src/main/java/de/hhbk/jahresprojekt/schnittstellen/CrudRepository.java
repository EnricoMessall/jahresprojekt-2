package de.hhbk.jahresprojekt.schnittstellen;

import java.util.List;
import java.util.Optional;

/**
 * @author Frederik Hafemann
 */
public interface CrudRepository<T> {
    Optional<T> findById(int id);
    List<T> findAll();
    T save(T object);
    void delete(T object) throws Exception;
}
