package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.entities.task.Task;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Log4j2

public class LoadTaskListTodayCommand extends LoadTaskListCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {

        List<Task> taskList = facadeService.getTodayTasksForUser(id);
        returnTskList(taskList);
    }
}
