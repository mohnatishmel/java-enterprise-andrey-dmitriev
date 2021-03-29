package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.model.task.Task;
import by.itacademy.model.user.User;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.service.Service;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Log4j2

public abstract class LoadTaskListCommand extends FrontCommand {

    protected int id;

    public LoadTaskListCommand() {
        this.id = ((User) SecurityContext.getInstance().getPrincipal()).getId();
    }

    protected void returnTskList(List<Task> taskList) throws IOException {
        String json = new Gson().toJson(taskList);
        returnResponse(json);
    }
}
