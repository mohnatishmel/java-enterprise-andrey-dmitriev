package by.itacademy.persistence.jpa.dao;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.persistence.jpa.query.holder.JpaJpqlQueryHolder;
import by.itacademy.persistence.jpa.query.initializer.QueryInitializer;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public abstract class AbstractJpaDaoJpa<T> {

    @PersistenceUnit
    protected EntityManagerFactory entityManagerFactory;

    protected final Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected abstract JpaJpqlQueryHolder getJpqlHolder();

    protected abstract QueryInitializer<T> getQueryInitializer();

    public T getById(int id) throws DaoException {
        EntityManager entityManager = null;
        T t = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            String queryString = getJpqlHolder().getByIdJpql();
            t = entityManager.createQuery(queryString, clazz)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id for " + clazz.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find by id for " + clazz.getSimpleName(), e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return t;
    }

    public List<T> getAll() throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<T> t = entityManager.createQuery(String.format("from %s", clazz.getSimpleName()), clazz).getResultList();
        entityManager.close();
        return t;
    }

    public T create(T t) throws DaoException {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (PersistenceException e) {
            throw new DaoException("Error creating entity", e);
        }
        return t;
    }

    public T update(T t) throws DaoException {
        EntityManager entityManager = null;

        EntityTransaction txn = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            txn = entityManager.getTransaction();
            txn.begin();
            String queryString = getJpqlHolder().updateJpql();
            Query query = entityManager.createQuery(queryString);
            getQueryInitializer().processUpdateQuery(query, t);
            query.executeUpdate();
            txn.commit();

        } catch (Exception e) {
            txn.rollback();
            e.printStackTrace();
            System.err.println("Error process update for " + clazz.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process update for " + clazz.getSimpleName(), e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return t;
    }

    public void delete(int id) throws DaoException {
        EntityManager entityManager = null;
        EntityTransaction txn = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            txn = entityManager.getTransaction();
            txn.begin();

            String queryString = getJpqlHolder().deleteJpql();
            entityManager.createQuery(queryString)
                    .setParameter("id", id)
                    .executeUpdate();
            txn.commit();

        } catch (Exception e) {
            txn.rollback();
            e.printStackTrace();
            System.err.println("Error process delete by id for " + clazz.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process delete by id for " + clazz.getSimpleName(), e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
