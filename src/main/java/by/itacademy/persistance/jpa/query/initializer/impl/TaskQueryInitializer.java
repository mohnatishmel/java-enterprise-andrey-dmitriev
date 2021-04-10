package by.itacademy.persistance.jpa.query.initializer.impl;


import by.itacademy.entities.task.Task;
import by.itacademy.persistance.jpa.query.initializer.QueryInitializer;

import javax.persistence.Query;

public class TaskQueryInitializer implements QueryInitializer<Task> {

    @Override
    public void processUpdateQuery(Query query, Task task) {

        query.setParameter("userId", task.getUserId());
        query.setParameter("name", task.getName());
        query.setParameter("description", task.getDescription());
        query.setParameter("deadLine", new java.sql.Date(task.getDeadLine().getTime()));
        query.setParameter("fixed", task.isFixed());
        query.setParameter("inBasket", task.isInBasket());
    }

}
