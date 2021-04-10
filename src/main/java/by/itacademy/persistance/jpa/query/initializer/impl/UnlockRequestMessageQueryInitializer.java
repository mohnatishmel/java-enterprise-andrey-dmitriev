package by.itacademy.persistance.jpa.query.initializer.impl;

import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.persistance.jpa.query.initializer.QueryInitializer;

import javax.persistence.Query;

public class UnlockRequestMessageQueryInitializer implements QueryInitializer<UnlockRequestMessage> {
    @Override
    public void processUpdateQuery(Query query, UnlockRequestMessage message) {
        query.setParameter("userId", message.getUserId());
        query.setParameter("userName", message.getUserName());
        query.setParameter("messageBody", message.getMessageBody());
    }
}
