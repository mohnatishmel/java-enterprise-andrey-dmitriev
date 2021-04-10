package by.itacademy.persistance.jpa.query.initializer.impl;

import by.itacademy.entities.user.User;
import by.itacademy.persistance.jpa.query.initializer.QueryInitializer;

import javax.persistence.Query;

public class UserQueryInitializer implements QueryInitializer<User> {

    @Override
    public void processUpdateQuery(Query query, User user) {
        query.setParameter("accountNotLocked", user.isAccountNotLocked());
    }

}
