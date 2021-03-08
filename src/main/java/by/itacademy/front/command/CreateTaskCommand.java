package by.itacademy.front.command;


import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.front.command.mapper.JsonToJavaTaskMapper;
import by.itacademy.model.task.Task;
import by.itacademy.service.Service;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;



@Log4j2

public class CreateTaskCommand extends FrontCommand {

    private Service service;

    {
        service = Service.getInstance();
    }

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {

        Task task = JsonToJavaTaskMapper.map(request);
        service.createTask(task);

        FrontCommand command = new LoadTaskListCommand();
        command.init(context, request, response);
        command.process();
    }
}
