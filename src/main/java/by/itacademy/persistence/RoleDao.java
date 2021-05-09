package by.itacademy.persistence;

import by.itacademy.entities.user.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {

    @Query("select r from Role r " +
            "JOIN FETCH r.users " +
            "WHERE r.roleName = :roleName")
    Role getRoleByRoleName(@Param("roleName")String roleName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM roles_map WHERE user_id = :userId",
            nativeQuery = true)
    void deleteByUserId(@Param("userId") int userId);

}