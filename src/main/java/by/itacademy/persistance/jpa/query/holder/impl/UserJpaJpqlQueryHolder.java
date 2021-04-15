package by.itacademy.persistance.jpa.query.holder.impl;

import by.itacademy.persistance.jpa.query.holder.JpaJpqlQueryHolder;

public class UserJpaJpqlQueryHolder implements JpaJpqlQueryHolder {

    private static final String GET_BY_ID_JPQL =
            "select u from User u " +
            "JOIN FETCH u.roles " +
            "JOIN FETCH u.personalInformation " +
            "JOIN FETCH u.credential " +
            "where u.id = :id";
    private static final String UPDATE_JPQL = "update User u set u.accountNotLocked = :accountNotLocked where u.id = :id";
    private static final String DELETE_JPQL = "delete User u where u.id = :id";

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
