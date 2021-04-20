package by.itacademy.persistence.jpa.dao.impl;

import by.itacademy.entities.file.File;
import by.itacademy.persistence.jpa.dao.AbstractJpaDaoJpa;
import by.itacademy.persistence.jpa.query.holder.JpaJpqlQueryHolder;
import by.itacademy.persistence.jpa.query.holder.impl.TaskFileJpaJpqlQueryHolder;
import by.itacademy.persistence.jpa.query.initializer.QueryInitializer;
import by.itacademy.persistence.jpa.query.initializer.impl.TaskFileQueryInitializer;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Log4j2

@Repository
public class TaskFileJpaDao extends AbstractJpaDaoJpa<File> {

    @Override
    protected JpaJpqlQueryHolder getJpqlHolder() {
        return new TaskFileJpaJpqlQueryHolder();
    }

    @Override
    protected QueryInitializer<File> getQueryInitializer() {
        return new TaskFileQueryInitializer();
    }
}
