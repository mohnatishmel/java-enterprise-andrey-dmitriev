package by.itacademy.dal;

import java.util.List;

public interface CrudDao<T> {

    T getById(long id);

    List<T> getAll();

    T create(T t);

    T update(T t);

    void delete(long id);

}
