package by.itacademy.persistance.jpa.dao.impl;

import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.persistance.jpa.dao.AbstractJpaDaoJpa;
import by.itacademy.persistance.jpa.query.holder.JpaJpqlQueryHolder;
import by.itacademy.persistance.jpa.query.holder.impl.UnlockRequestMessageJpaJpqlQueryHolder;
import by.itacademy.persistance.jpa.query.initializer.QueryInitializer;
import by.itacademy.persistance.jpa.query.initializer.impl.UnlockRequestMessageQueryInitializer;

public class UnlockRequestMessageJpaDao extends AbstractJpaDaoJpa<UnlockRequestMessage> {

    private static UnlockRequestMessageJpaDao instance;

    private UnlockRequestMessageJpaDao() {
    }

    @Override
    protected JpaJpqlQueryHolder getJpqlHolder() {
        return new UnlockRequestMessageJpaJpqlQueryHolder();
    }

    @Override
    protected QueryInitializer<UnlockRequestMessage> getQueryInitializer() {
        return new UnlockRequestMessageQueryInitializer();
    }

    public static UnlockRequestMessageJpaDao getInstance() {
        if (instance == null) {
            instance = new UnlockRequestMessageJpaDao();
        }
        return instance;
    }
}
