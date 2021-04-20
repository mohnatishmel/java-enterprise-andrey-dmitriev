package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.entities.file.File;
import by.itacademy.persistence.jpa.dao.impl.TaskFileJpaDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Log4j2

@Service
public class FileService {

    private TaskFileJpaDao taskFleJdbcDao;

    @Autowired
    public FileService(TaskFileJpaDao taskFleJdbcDao) {
        this.taskFleJdbcDao = taskFleJdbcDao;
    }

    public void uploadFileForTask(File file) throws ApplicationBasedException {
        try {
            taskFleJdbcDao.create(file);
        } catch (Exception e) {
            log.debug(Arrays.toString(e.getStackTrace()));
            try {
                taskFleJdbcDao.update(file);
            } catch (DaoException daoException) {
                log.debug(Arrays.toString(e.getStackTrace()));
                throw new ApplicationBasedException(e);
            }
        }
    }

    public File getFile(long id) throws ApplicationBasedException {
        try {
            return taskFleJdbcDao.getById((int) id);
        } catch (DaoException e) {
            log.debug(Arrays.toString(e.getStackTrace()));
            throw new ApplicationBasedException(e);
        }
    }
}
