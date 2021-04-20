package by.itacademy.persistence;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.entities.task.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface TaskDao extends PagingAndSortingRepository<Task, Integer> {

    Task getById(int id) throws DaoException;

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tasks " +
            "user_id = :userId, " +
            "task_name = :name, " +
            "description = :description, " +
            "deadline = :deadline, " +
            "fixed = :fixed, " +
            "in_basket = :inBasket",
            nativeQuery = true)
    Task create(@Param("userId") int userId,
                @Param("name") String name,
                @Param("description") String description,
                @Param("deadline") Date deadline,
                @Param("fixed") boolean fixed,
                @Param("inBasket") boolean inBasket) throws DaoException;

    @Query("from Task t where t.userId = :userId")
    List<Task> getByUserId(@Param("userId") int userId) throws DaoException;

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tasks WHERE user_id = :userId",
            nativeQuery = true)
    void deleteByUserId(@Param("userId") int userId) throws DaoException;

}
