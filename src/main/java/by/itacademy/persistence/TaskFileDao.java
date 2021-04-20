package by.itacademy.persistence;

import by.itacademy.entities.file.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskFileDao extends CrudRepository<File, Integer> {
}
