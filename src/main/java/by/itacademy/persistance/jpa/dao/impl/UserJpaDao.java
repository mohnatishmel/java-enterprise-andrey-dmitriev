package by.itacademy.persistance.jpa.dao.impl;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.security.model.user.UserDetails;
import by.itacademy.entities.user.User;
import by.itacademy.persistance.UserDao;
import by.itacademy.persistance.jpa.dao.AbstractJpaDaoJpa;
import by.itacademy.persistance.jpa.query.holder.JpaJpqlQueryHolder;
import by.itacademy.persistance.jpa.query.holder.impl.UserJpaJpqlQueryHolder;
import by.itacademy.persistance.jpa.query.initializer.QueryInitializer;
import by.itacademy.persistance.jpa.query.initializer.impl.UserQueryInitializer;

import javax.persistence.EntityManager;

public class UserJpaDao extends AbstractJpaDaoJpa<User> implements UserDao {

    private static UserJpaDao instance;

    private UserJpaDao() {
    }

    @Override
    public UserDetails getByName(String name) throws UsernameNotFoundException {
        EntityManager entityManager = null;
        User user = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            user = entityManager.createQuery("select u from User u " +
                    "JOIN FETCH u.roles" +
                    "JOIN FETCH u.personalInformation" +
                    "JOIN JOIN u.credential c ON c.login = :login ", clazz)
                    .setParameter("login", name)
                    .getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id for " + clazz.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

        return user;
    }

    @Override
    public User update(User user) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        user = entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public void delete(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = entityManager.find(clazz, id);
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    protected JpaJpqlQueryHolder getJpqlHolder() {
        return new UserJpaJpqlQueryHolder();
    }

    @Override
    protected QueryInitializer<User> getQueryInitializer() {
        return new UserQueryInitializer();
    }

    public static UserJpaDao getInstance() {
        if (instance == null) {
            instance = new UserJpaDao();
        }
        return instance;
    }
}
