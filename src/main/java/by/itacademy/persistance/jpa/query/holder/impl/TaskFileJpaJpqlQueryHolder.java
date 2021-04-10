package by.itacademy.persistance.jpa.query.holder.impl;


import by.itacademy.persistance.jpa.query.holder.JpaJpqlQueryHolder;

public class TaskFileJpaJpqlQueryHolder implements JpaJpqlQueryHolder {

    private static final String GET_BY_ID_JPQL = "from File f where f.task_file_id = :id";
    private static final String UPDATE_JPQL = "update File f set f.file_name = :name, f.file = :file where f.task_file_id = :id";
    private static final String DELETE_JPQL = "delete File f where f.task_file_id = :id";

    @Override
    public String getByIdJpql() {
        return GET_BY_ID_JPQL;
    }

    @Override
    public String updateJpql() {
        return UPDATE_JPQL;
    }

    @Override
    public String deleteJpql() {
        return DELETE_JPQL;
    }
}

