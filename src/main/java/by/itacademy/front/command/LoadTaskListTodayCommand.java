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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Log4j2

public class LoadTaskListTodayCommand extends LoadTaskListCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {

        List<Task> taskList = service.getTodayTasksForUser(id);
        returnTskList(taskList);
    }
}
