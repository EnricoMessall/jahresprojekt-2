package de.hhbk.jahresprojekt.database;

import de.hhbk.jahresprojekt.schnittstellen.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class Repository<T>  implements CrudRepository<T> {

    private final Class<T> tClass;

    public Repository(Class<T> tClass){
        this.tClass = tClass;
    }

    @Override
    public Optional<T> findById(int id) throws HibernateException {
        SessionFactory sessionFactory = HibernateManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Optional<T> optional = Optional.of(session.get(tClass, (long)id));
        session.getTransaction().commit();
        session.close();
        return optional;
    }

    @Override
    public List<T> findAll() throws HibernateException {
        SessionFactory sessionFactory = HibernateManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> rootEntry = cq.from(tClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = session.createQuery(all);
        session.getTransaction().commit();
        List<T> result = allQuery.getResultList();
        session.close();
        return result;
    }

    @Override
    public T save(T object) throws HibernateException {
        SessionFactory sessionFactory = HibernateManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        session.close();
        return object;
    }

    @Override
    public void delete(T object) throws HibernateException {
        SessionFactory sessionFactory = HibernateManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }

    protected String getTableName(){
        return tClass.getAnnotation(Table.class) == null ? tClass.getName() :
                tClass.getAnnotation(Table.class).name();
    }
}
