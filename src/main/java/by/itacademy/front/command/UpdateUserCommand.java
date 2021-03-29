package by.itacademy.front.command;


import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.front.command.mapper.JsonToJavaTaskMapper;
import by.itacademy.front.command.mapper.JsonToJavaUserMapper;
import by.itacademy.model.task.Task;
import by.itacademy.model.user.User;
import by.itacademy.service.Service;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Log4j2

public class UpdateUserCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        User user = JsonToJavaUserMapper.map(request);
        service.updateUser(user);

        Message message = new Message("User successfully updated");

        String json = new Gson().toJson(message);
        returnResponse(json);
    }
}
