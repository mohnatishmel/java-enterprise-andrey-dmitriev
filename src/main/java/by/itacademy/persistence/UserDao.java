package by.itacademy.persistence;


import by.itacademy.exception.dao.DaoException;
import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.security.model.authentication.UserDetailService;
import by.itacademy.entities.user.User;
import by.itacademy.security.model.user.UserDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserDao extends PagingAndSortingRepository<User, Integer>, UserDetailService {

    @Override
    @Query("select u from User u " +
            "JOIN FETCH u.roles " +
            "JOIN FETCH u.personalInformation " +
            "JOIN FETCH u.credential " +
            "JOIN u.credential c ON c.login = :login")
    UserDetails getByName(@Param("login") String login) throws UsernameNotFoundException;

    @Query("select distinct u from User u " +
            "JOIN FETCH u.roles " +
            "JOIN FETCH u.personalInformation " +
            "JOIN FETCH u.credential " +
            "JOIN FETCH u.credential")
    List<User> getAll() throws DaoException;

    @Query("select u from User u " +
            "JOIN FETCH u.roles " +
            "JOIN FETCH u.personalInformation " +
            "JOIN FETCH u.credential " +
            "where u.id = :id")
    User getById(@Param("id") int id) throws DaoException;

//    @Modifying
//    @Transactional
//    @Query("")
//    User create(User user) throws DaoException;

//    @Modifying
//    @Transactional
//    User (User user) throws DaoException;

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE user_id = :id",
            nativeQuery = true)
    void delete(@Param("id") int id) throws DaoException;

}
