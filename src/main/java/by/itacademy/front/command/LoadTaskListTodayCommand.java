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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Log4j2

public class LoadTaskListTodayCommand extends LoadTaskListCommand {

    private Service service;

    {
        service = Service.getInstance();
    }

    @Override
    public void process() throws ServletException, IOException {
         List<Task> taskList = loadTskListForCurrentUser();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long today = calendar.getTimeInMillis();
        System.out.println(new Date(today));

        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);

        long tomorrow = calendar.getTimeInMillis();
        System.out.println(new Date(tomorrow));

        List<Task> todayTaskList = new ArrayList<>();
        for (Task task : taskList) {
            long i = task.getDeadLine().getTime();
            System.out.println(task.getDeadLine());
            System.out.println(tomorrow - task.getDeadLine().getTime());
            if (task.getDeadLine().getTime() >= today
                    && task.getDeadLine().getTime() < tomorrow
                    && !task.isInBasket()
                    && !task.isFixed()) {
                todayTaskList.add(task);
            }
        }

        returnTskList(todayTaskList);
    }
}
