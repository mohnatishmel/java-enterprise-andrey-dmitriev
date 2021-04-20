package by.itacademy.front.command;


import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.front.mapper.impl.JsonToJavaTaskMapper;
import by.itacademy.entities.task.Task;
import by.itacademy.service.FacadeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import java.io.IOException;

@Log4j2
public class UpdateTaskCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {

        Task task = new JsonToJavaTaskMapper().map(request);
        facadeService.updateTask(task);

        returnMessage(String.format("Task_%s was successfully updated", task.getId()), 200);
    }
}
