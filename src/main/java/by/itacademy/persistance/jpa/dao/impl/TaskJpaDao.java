package by.itacademy.persistance.jpa.dao.impl;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.entities.task.Task;
import by.itacademy.persistance.TaskDao;
import by.itacademy.persistance.jpa.dao.AbstractJpaDaoJpa;
import by.itacademy.persistance.jpa.query.holder.JpaJpqlQueryHolder;
import by.itacademy.persistance.jpa.query.holder.impl.TaskJpaJpqlQueryHolder;
import by.itacademy.persistance.jpa.query.initializer.QueryInitializer;
import by.itacademy.persistance.jpa.query.initializer.impl.TaskQueryInitializer;
import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Log4j2

public class TaskJpaDao extends AbstractJpaDaoJpa<Task> implements TaskDao {

    private static TaskJpaDao instance;

    private TaskJpaDao() {
    }

    @Override
    protected JpaJpqlQueryHolder getJpqlHolder() {
        return new TaskJpaJpqlQueryHolder();
    }

    @Override
    protected QueryInitializer<Task> getQueryInitializer() {
        return new TaskQueryInitializer();
    }

    @Override
    public Task create(Task task) throws DaoException {
       EntityManager em = entityManagerFactory.createEntityManager();
       em.getTransaction().begin();
        Query q = em.createNativeQuery("INSERT INTO tasks(user_id, task_name, description, deadline, fixed, in_basket) VALUES(?,?,?,?,?,?)");
        q.setParameter(1, task.getUserId());
        q.setParameter(2, task.getName());
        q.setParameter(3, task.getDescription());
        q.setParameter(4, new java.sql.Date(task.getDeadLine().getTime()));
        q.setParameter(5, task.isFixed());
        q.setParameter(6, task.isInBasket());

        q.executeUpdate();
        em.getTransaction().commit();
        return task;
    }

    @Override
    public List<Task> getByUserId(int id) throws DaoException {
        EntityManager entityManager = null;
        List<Task> tasks;

        try {
            entityManager = entityManagerFactory.createEntityManager();
//            String queryString = "from Task t where t.userId = :userId";
            tasks = entityManager.createQuery("from Task t where t.userId = :userId", clazz)
                    .setParameter("userId", id)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id for " + clazz.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find by id for " + clazz.getSimpleName(), e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return tasks;
    }

    @Override
    public void deleteByUserId(int userId) throws DaoException {
        EntityManager entityManager = null;
        EntityTransaction txn = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            txn = entityManager.getTransaction();
            txn.begin();

            String queryString = getJpqlHolder().deleteJpql();
            entityManager.createQuery("delete Task t where t.userId = :userId")
                    .setParameter("id", userId)
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

    public static TaskJpaDao getInstance() {
        if (instance == null) {
            instance = new TaskJpaDao();
        }
        return instance;
    }

}
