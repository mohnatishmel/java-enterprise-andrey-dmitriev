package by.itacademy.persistence.jpa.query.initializer;

import javax.persistence.Query;

public interface QueryInitializer<T> {

    void processUpdateQuery(Query query, T t);

}
