package by.itacademy.persistence;

import by.itacademy.entities.user.PersonalInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInformationDao extends CrudRepository<PersonalInformation, Integer> {
}
