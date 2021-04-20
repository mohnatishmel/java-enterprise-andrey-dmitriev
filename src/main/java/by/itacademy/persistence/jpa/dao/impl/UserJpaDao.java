package by.itacademy.persistence.jpa.dao.impl;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.security.model.user.UserDetails;
import by.itacademy.entities.user.User;
import by.itacademy.persistence.UserDao;
import by.itacademy.persistence.jpa.dao.AbstractJpaDaoJpa;
import by.itacademy.persistence.jpa.query.holder.JpaJpqlQueryHolder;
import by.itacademy.persistence.jpa.query.holder.impl.UserJpaJpqlQueryHolder;
import by.itacademy.persistence.jpa.query.initializer.QueryInitializer;
import by.itacademy.persistence.jpa.query.initializer.impl.UserQueryInitializer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserJpaDao extends AbstractJpaDaoJpa<User> implements UserDao {

    @Override
    public UserDetails getByName(String name) throws UsernameNotFoundException {
        EntityManager entityManager = null;
        User user = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            user = entityManager.createQuery("select u from User u " +
                    "JOIN FETCH u.roles " +
                    "JOIN FETCH u.personalInformation " +
                    "JOIN FETCH u.credential " +
//                    "JOIN FETCH u.tasks " +
                    "JOIN u.credential c ON c.login = :login ", clazz)
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
    public List<User> getAll() throws DaoException {
        EntityManager entityManager = null;
        List<User> users = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            users = entityManager.createQuery("select distinct u from User u " +
                    "JOIN FETCH u.roles " +
                    "JOIN FETCH u.personalInformation " +
                    "JOIN FETCH u.credential " +
                    "JOIN FETCH u.credential", clazz)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id for " + clazz.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

        return users;
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
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User user = getById(id);

        Query q = em.createNativeQuery("DELETE FROM tasks WHERE user_id = :id");
        q.setParameter("id", id);
        q.executeUpdate();

        q = em.createNativeQuery("DELETE FROM unlock_request_messages WHERE user_id = :id");
        q.setParameter("id", id);
        q.executeUpdate();

        q = em.createNativeQuery("DELETE FROM roles_map WHERE user_id = :id");
        q.setParameter("id", id);
        q.executeUpdate();

        q = em.createNativeQuery("DELETE FROM users WHERE user_id = :id");
        q.setParameter("id", id);
        q.executeUpdate();

        q = em.createNativeQuery("DELETE FROM personal_information WHERE personal_information_id = :personalInformationId");
        q.setParameter("personalInformationId", user.getPersonalInformation().getId());
        q.executeUpdate();

        q = em.createNativeQuery("DELETE FROM credentials WHERE credentials_id = :credentialsId");
        q.setParameter("credentialsId", user.getCredential().getId());
        q.executeUpdate();

        em.getTransaction().commit();
        em.close();

    }

    @Override
    protected JpaJpqlQueryHolder getJpqlHolder() {
        return new UserJpaJpqlQueryHolder();
    }

    @Override
    protected QueryInitializer<User> getQueryInitializer() {
        return new UserQueryInitializer();
    }
}
