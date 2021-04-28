package by.itacademy.persistence;

import by.itacademy.entities.user.Credential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsDao extends CrudRepository<Credential, Integer> {
}
