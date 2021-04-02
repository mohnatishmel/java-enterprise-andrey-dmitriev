package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.file.File;
import by.itacademy.persistance.jdbc.dao.file.TaskFileJdbcDao;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

@Log4j2

public class FileService {

    private static FileService instance;

    private TaskFileJdbcDao taskFleJdbcDao;

    {
        taskFleJdbcDao = TaskFileJdbcDao.getInstance();
    }

    public void uploadFileForTask(File file) throws ApplicationBasedException {
        try {
            taskFleJdbcDao.create(file);
        } catch (DaoException e) {
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

    public static FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
        }
        return instance;
    }
}
