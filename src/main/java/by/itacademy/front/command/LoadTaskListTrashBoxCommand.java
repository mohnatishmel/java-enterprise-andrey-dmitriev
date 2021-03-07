package by.itacademy.front.command;

import by.itacademy.model.task.Task;
import by.itacademy.model.user.User;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.service.Service;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2

public class LoadTaskListTrashBoxCommand extends LoadTaskListCommand {

    private Service service;

    {
        service = Service.getInstance();
    }

    @Override
    public void process() throws ServletException, IOException {
        List<Task> taskList = loadTskListForCurrentUser();
        List<Task> trashBox = new ArrayList<>();

        for (Task task : taskList) {
            if (task.isInBasket()) {
                trashBox.add(task);
            }
        }
        returnTskList(trashBox);
    }
}
