package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.entities.task.Task;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;

import javax.servlet.ServletException;
import java.io.IOException;

@Log4j2
public class LoadTaskListTrashBoxCommand extends LoadTaskListCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {
        Page<Task> taskList = facadeService.getTrashBoxTasksForUser(id, pageable);
        returnTskList(taskList);
    }
}
