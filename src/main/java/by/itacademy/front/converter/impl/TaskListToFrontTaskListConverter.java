package by.itacademy.front.converter.impl;

import by.itacademy.entities.front.FrontTask;
import by.itacademy.entities.task.Task;
import by.itacademy.front.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskListToFrontTaskListConverter implements Converter<List<Task>, List<FrontTask>> {

    private TaskToFrontTaskConverter converter = new TaskToFrontTaskConverter();

    @Override
    public List<FrontTask> convert(List<Task> tasks) {
        List<FrontTask> frontTasks = new ArrayList<>();
        for (Task task : tasks) {
            frontTasks.add(converter.convert(task));
        }
        return frontTasks;
    }
}
