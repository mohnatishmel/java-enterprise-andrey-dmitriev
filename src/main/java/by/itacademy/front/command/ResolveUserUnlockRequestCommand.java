package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.front.command.mapper.JsonToJavaUnlockRequestMessageMapper;
import by.itacademy.model.message.UnlockRequestMessage;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import java.io.IOException;

public class ResolveUserUnlockRequestCommand extends FrontCommand{

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {
        UnlockRequestMessage unlockMessage = JsonToJavaUnlockRequestMessageMapper.map(request);

        service.resolveUnlockUserRequest(unlockMessage);

        String messageBody = "Request was successfully resolved";
        Message message = new Message(messageBody);
        String json = new Gson().toJson(message);
        returnResponse(json);
    }
}
