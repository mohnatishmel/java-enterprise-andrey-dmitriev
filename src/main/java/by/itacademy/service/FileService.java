package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.file.File;
import by.itacademy.exception.FileNotFoundException;
import by.itacademy.persistence.TaskFileDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Log4j2

@Service
public class FileService {

    final private TaskFileDao taskFileDao;
    final private TaskService taskService;

    public void uploadFileForTask(File file) throws ApplicationBasedException {
        try {
            taskFileDao.save(file);
            taskService.updateTaskHasFile(file.getId(), true);

        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public File getFile(long id) throws ApplicationBasedException {
        try {
           return taskFileDao.findById((int) id).orElseThrow(() -> new FileNotFoundException("No files for this task"));

        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }
}
