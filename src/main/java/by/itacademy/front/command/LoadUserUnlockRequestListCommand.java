package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.model.message.UnlockRequestMessage;
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

public class LoadUserUnlockRequestListCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {
        List<UnlockRequestMessage> messageList = service.getUnlockRequestMessages();
        String json = new Gson().toJson(messageList);
        returnResponse(json);
    }
}
