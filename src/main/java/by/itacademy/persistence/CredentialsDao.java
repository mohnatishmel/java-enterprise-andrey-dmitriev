package by.itacademy.persistence;

import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.entities.user.Credential;
import by.itacademy.exception.dao.DaoException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CredentialsDao extends CrudRepository<Credential, Integer> {
}
