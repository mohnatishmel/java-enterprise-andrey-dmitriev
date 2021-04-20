package by.itacademy.persistence.jpa.dao.impl;

import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.persistence.jpa.dao.AbstractJpaDaoJpa;
import by.itacademy.persistence.jpa.query.holder.JpaJpqlQueryHolder;
import by.itacademy.persistence.jpa.query.holder.impl.UnlockRequestMessageJpaJpqlQueryHolder;
import by.itacademy.persistence.jpa.query.initializer.QueryInitializer;
import by.itacademy.persistence.jpa.query.initializer.impl.UnlockRequestMessageQueryInitializer;
import org.springframework.stereotype.Repository;

@Repository
public class UnlockRequestMessageJpaDao extends AbstractJpaDaoJpa<UnlockRequestMessage> {

    @Override
    protected JpaJpqlQueryHolder getJpqlHolder() {
        return new UnlockRequestMessageJpaJpqlQueryHolder();
    }

    @Override
    protected QueryInitializer<UnlockRequestMessage> getQueryInitializer() {
        return new UnlockRequestMessageQueryInitializer();
    }
}
