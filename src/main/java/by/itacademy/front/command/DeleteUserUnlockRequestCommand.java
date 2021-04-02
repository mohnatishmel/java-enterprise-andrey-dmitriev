package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.front.mapper.impl.JsonToJavaUnlockRequestMessageMapper;
import by.itacademy.model.message.UnlockRequestMessage;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteUserUnlockRequestCommand extends FrontCommand{

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {
        UnlockRequestMessage unlockMessage = new JsonToJavaUnlockRequestMessageMapper().map(request);

        facadeService.deleteUnlockUserRequest(unlockMessage);

        String messageBody = "Request was successfully deleted";
        Message message = new Message(messageBody);
        String json = new Gson().toJson(message);
        returnResponse(json);
    }
}
