package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.entities.task.Task;
import by.itacademy.front.converter.impl.TaskListToFrontTaskListConverter;
import by.itacademy.service.FacadeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Log4j2
public class LoadTaskListFixedCommand extends LoadTaskListCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {
        List<Task> taskList = facadeService.getFixedTasksForUser(id);
        returnTskList(taskList);
    }
}
