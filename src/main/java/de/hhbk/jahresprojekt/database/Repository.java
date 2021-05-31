package de.hhbk.jahresprojekt.database;

import de.hhbk.jahresprojekt.schnittstellen.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

public class Repository<T, ID>  implements CrudRepository<T, ID> {

    private Class<T> tClass;

    protected Repository(Class<T> tClass){
        this.tClass = tClass;
    }

    @Override
    public T findById(ID id) {
        T object;
        SessionFactory sessionFactory = HibernateManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        object = session.get(tClass, (Serializable) id);
        session.getTransaction().commit();
        session.close();
        return object;
    }

    @Override
    public List<T> findAll() {
        List<T> object;
        SessionFactory sessionFactory = HibernateManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String tableName = tClass.getAnnotation(Table.class) == null ? tClass.getName() : tClass.getAnnotation(Table.class).name();
        object = session.createQuery("from " + tableName).list();
        session.getTransaction().commit();
        session.close();
        return object;
    }

    @Override
    public T save(T object) {
        SessionFactory sessionFactory = HibernateManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        session.close();
        return object;
    }

    @Override
    public void delete(T object) {
        SessionFactory sessionFactory = HibernateManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }
}
