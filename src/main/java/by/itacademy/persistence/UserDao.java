package by.itacademy.persistence;

import by.itacademy.entities.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends PagingAndSortingRepository<User, Integer> {

    @Query("select u from User u " +
            "JOIN FETCH u.roles " +
            "JOIN FETCH u.personalInformation " +
            "JOIN FETCH u.credential " +
            "JOIN u.credential c ON c.login = :login")
    Optional<User> getByName(@Param("login") String login) ;

    @Query("select distinct u from User u " +
            "JOIN FETCH u.roles " +
            "JOIN FETCH u.personalInformation " +
            "JOIN FETCH u.credential " +
            "JOIN FETCH u.credential")
    List<User> getAll();

    @Query("select u from User u " +
            "JOIN FETCH u.roles " +
            "JOIN FETCH u.personalInformation " +
            "JOIN FETCH u.credential " +
            "where u.id = :id")
    User getById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE user_id = :id",
            nativeQuery = true)
    void delete(@Param("id") int id);

    @Query("select u from User u " +
            "JOIN FETCH u.roles " +
            "JOIN FETCH u.personalInformation " +
            "JOIN FETCH u.credential " +
            "where u.accountNotLocked = :accountNotLocked " +
            "and u.id <> :id")
    List<User> getByAccountNonLocked(@Param("accountNotLocked") boolean accountNotLocked,@Param("id") int excludeId);
}
