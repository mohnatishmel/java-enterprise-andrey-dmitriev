package by.itacademy.front.command;


import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.front.mapper.impl.JsonToJavaTaskMapper;
import by.itacademy.model.task.Task;
import by.itacademy.service.FacadeService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Log4j2

public class UpdateTaskCommand extends FrontCommand {

    private FacadeService facadeService;

    {
        facadeService = FacadeService.getInstance();
    }

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        Task task = new JsonToJavaTaskMapper().map(request);
        facadeService.updateTask(task);

        returnMessage(String.format("Task_%s was successfully updated", task.getId()), 200);
    }
}
