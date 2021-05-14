package by.itacademy.persistence;

import by.itacademy.entities.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Task getById(int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tasks(user_id, task_name, description, deadline, fixed, in_basket, has_file) " +
            "VALUES(:userId,:name,:description,:deadline,:fixed,:inBasket,:hasFile);",
            nativeQuery = true)
    void create(@Param("userId") int userId,
                @Param("name") String name,
                @Param("description") String description,
                @Param("deadline") Date deadline,
                @Param("fixed") boolean fixed,
                @Param("inBasket") boolean inBasket,
                @Param("hasFile") boolean hasFile);

    @Query("from Task t where t.userId = :userId")
    List<Task> getByUserId(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Task WHERE userId = :userId")
    void deleteByUserId(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query("update Task t set " +
            "t.name = :name, " +
            "t.description = :description, " +
            "t.deadLine = :deadLine, " +
            "t.fixed = :fixed, " +
            "t.inBasket = :inBasket, " +
            "t.hasFile = :hasFile " +
            "where t.id = :id")

    void update(@Param("id") int id,
                @Param("name") String name,
                @Param("description") String description,
                @Param("deadLine") Date deadLine,
                @Param("fixed") boolean fixed,
                @Param("inBasket") boolean inBasket,
                @Param("hasFile") boolean hasFile);

    Page<Task> getByDeadLineIsLessThanEqualAndUserIdAndFixedIsFalseAndInBasketIsFalse(Date dateStart, int id, Pageable pageable);
    Page<Task> getByDeadLineBetweenAndUserIdAndFixedIsFalseAndInBasketIsFalse(Date dateStart, Date dateEnd, int id, Pageable pageable);
    Page<Task> getByDeadLineIsGreaterThanAndUserIdAndFixedIsFalseAndInBasketIsFalse(Date data, int id, Pageable pageable);
    Page<Task> getByFixedIsTrueAndUserId(int id, Pageable pageable);
    Page<Task> getByInBasketIsTrueAndUserId(int id, Pageable pageable);
}
