package by.itacademy.front.command;


import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.front.mapper.impl.JsonToJavaTaskMapper;
import by.itacademy.entities.task.Task;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;



@Log4j2
public class CreateTaskCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {

        Task task = new JsonToJavaTaskMapper().map(request);
//        task.setUser((User) SecurityContext.getInstance().getPrincipal());
        facadeService.createTask(task);

        String messageBody = "The task was successfully created";
        Message message = new Message(messageBody);
        String json = new Gson().toJson(message);
        returnResponse(json);
    }
}
