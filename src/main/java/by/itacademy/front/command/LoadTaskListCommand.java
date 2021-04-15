package by.itacademy.front.command;

import by.itacademy.entities.front.FrontTask;
import by.itacademy.entities.task.Task;
import by.itacademy.front.converter.impl.TaskListToFrontTaskListConverter;
import by.itacademy.security.service.SecurityContext;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

@Log4j2

public abstract class LoadTaskListCommand extends FrontCommand {

    private TaskListToFrontTaskListConverter converter = new TaskListToFrontTaskListConverter();

    protected int id;

    public LoadTaskListCommand() {
        this.id = SecurityContext.getInstance().getPrincipal().getId();
    }

    protected void returnTskList(List<Task> taskList) throws IOException {
        List<FrontTask> frontTasks = converter.convert(taskList);
        String json = new Gson().toJson(frontTasks);
        returnResponse(json);
    }

    protected List<FrontTask> convert(List<Task> tasks) {
        return converter.convert(tasks);
    }
}
