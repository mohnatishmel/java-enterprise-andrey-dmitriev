package by.itacademy.front.command;

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

public class LoadTaskListCommand extends FrontCommand {

    private Service service;

    {
        service = Service.getInstance();
    }

    @Override
    public void process() throws ServletException, IOException {
        List<Task> taskList = loadTskListForCurrentUser();
        returnTskList(taskList);
    }

    protected List<Task> loadTskListForCurrentUser() {
        User user = (User) SecurityContext.getInstance().getPrincipal();
        return service.getTasksForUser(user.getId());
    }

    protected void returnTskList(List<Task> taskList) throws IOException {

        String json = new Gson().toJson(taskList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
