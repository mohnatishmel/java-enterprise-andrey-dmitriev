package by.itacademy.persistance.jpa.query.holder.impl;

import by.itacademy.persistance.jpa.query.holder.JpaJpqlQueryHolder;

public class UnlockRequestMessageJpaJpqlQueryHolder implements JpaJpqlQueryHolder {

    private static final String GET_BY_ID_JPQL = "from UnlockRequestMessage u where u.id = :id";
    private static final String UPDATE_JPQL = "update UnlockRequestMessage u set u.userId = :userId, u.userName = :userName, u.messageBody= :messageBody where u.id = :id";
    private static final String DELETE_JPQL = "delete UnlockRequestMessage u where u.id = :id";

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
