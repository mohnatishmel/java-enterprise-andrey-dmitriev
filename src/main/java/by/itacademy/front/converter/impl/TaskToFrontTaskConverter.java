package by.itacademy.front.converter.impl;

import by.itacademy.entities.front.FrontTask;
import by.itacademy.entities.task.Task;
import by.itacademy.front.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskToFrontTaskConverter implements Converter<Task, FrontTask> {

    @Override
    public FrontTask convert(Task task) {
        return FrontTask.builder()
                .id(task.getId())
                .deadLine(task.getDeadLine())
                .description(task.getDescription())
                .fixed(task.isFixed())
                .inBasket(task.isInBasket())
                .name(task.getName())
                .userId(task.getUserId())
                .build();
    }
}
