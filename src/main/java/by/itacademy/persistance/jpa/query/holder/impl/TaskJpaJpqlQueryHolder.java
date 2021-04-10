package by.itacademy.persistance.jpa.query.holder.impl;

import by.itacademy.persistance.jpa.query.holder.JpaJpqlQueryHolder;

public class TaskJpaJpqlQueryHolder implements JpaJpqlQueryHolder {

    private static final String GET_BY_ID_JPQL = "from Task t where t.id = :id";
    private static final String UPDATE_JPQL = "update Task t set t.name = :name, t.description = :description, t.deadline = :deadline, t.fixed = :fixed, t.inBasket = :inBasket where t.id = :id";
    private static final String DELETE_JPQL = "delete Task t where t.id = :id";

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
