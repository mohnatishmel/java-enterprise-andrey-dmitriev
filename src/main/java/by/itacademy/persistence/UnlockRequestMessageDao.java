package by.itacademy.persistence;

import by.itacademy.entities.message.UnlockRequestMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UnlockRequestMessageDao extends JpaRepository<UnlockRequestMessage, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM UnlockRequestMessage WHERE userId = :userId")
    void deleteByUserId(@Param("userId") int userId);


}
