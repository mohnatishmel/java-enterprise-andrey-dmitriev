package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.model.task.Task;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Log4j2

public class LoadTaskListTomorrowCommand extends LoadTaskListCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {
        List<Task> taskList = facadeService.getTomorrowTasksForUser(id);
        returnTskList(taskList);
    }
}
