package de.hhbk.jahresprojekt.schnittstellen;

import java.util.List;

/**
 * @author Frederik Hafemann
 */
public interface CrudRepository<T, ID> {
    T findById(ID id);
    List<T> findAll();
    T save(T object);
    void delete(T object);
}
