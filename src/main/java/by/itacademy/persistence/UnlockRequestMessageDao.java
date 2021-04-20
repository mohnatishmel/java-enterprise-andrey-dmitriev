package by.itacademy.persistence;

import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.exception.dao.DaoException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UnlockRequestMessageDao extends CrudRepository<UnlockRequestMessage, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM unlock_request_messages WHERE user_id = :userId",
            nativeQuery = true)
    void deleteByUserId(@Param("userId") int userId) throws DaoException;


}
