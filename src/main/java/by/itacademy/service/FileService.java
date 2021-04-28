package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.file.File;
import by.itacademy.persistence.TaskFileDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Log4j2

@Service
public class FileService {

    private TaskFileDao taskFileDao;

    @Autowired
    public FileService(TaskFileDao taskFileDao) {
        this.taskFileDao = taskFileDao;
    }

    public void uploadFileForTask(File file) throws ApplicationBasedException {
        try {
            taskFileDao.save(file);
        } catch (DataAccessException e) {
            log.debug(Arrays.toString(e.getStackTrace()));
            try {
                taskFileDao.save(file);

            } catch (DataAccessException e1) {
                log.debug(Arrays.toString(e1.getStackTrace()));
                throw new ApplicationBasedException(e1);
            }
        }
    }

    public File getFile(long id) throws ApplicationBasedException {
        try {
            Optional<File> optionalFile = taskFileDao.findById((int) id);
            if (optionalFile.isPresent()) {
                return optionalFile.get();
            }
            throw new ApplicationBasedException("file not found");

        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }
}
