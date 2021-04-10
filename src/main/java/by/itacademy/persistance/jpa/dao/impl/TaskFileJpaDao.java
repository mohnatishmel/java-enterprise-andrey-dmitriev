package by.itacademy.persistance.jpa.dao.impl;

import by.itacademy.entities.file.File;
import by.itacademy.persistance.jpa.dao.AbstractJpaDaoJpa;
import by.itacademy.persistance.jpa.query.holder.JpaJpqlQueryHolder;
import by.itacademy.persistance.jpa.query.holder.impl.TaskFileJpaJpqlQueryHolder;
import by.itacademy.persistance.jpa.query.initializer.QueryInitializer;
import by.itacademy.persistance.jpa.query.initializer.impl.TaskFileQueryInitializer;
import lombok.extern.log4j.Log4j2;

@Log4j2

public class TaskFileJpaDao extends AbstractJpaDaoJpa<File> {

    private static TaskFileJpaDao instance;

    private TaskFileJpaDao() {
    }

    @Override
    protected JpaJpqlQueryHolder getJpqlHolder() {
        return new TaskFileJpaJpqlQueryHolder();
    }

    @Override
    protected QueryInitializer<File> getQueryInitializer() {
        return new TaskFileQueryInitializer();
    }

    public static TaskFileJpaDao getInstance() {
        if (instance == null) {
            instance = new TaskFileJpaDao();
        }
        return instance;
    }

}
