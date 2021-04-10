package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.entities.message.UnlockRequestMessage;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Log4j2

public class LoadUserUnlockRequestListCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {
        List<UnlockRequestMessage> messageList = facadeService.getUnlockRequestMessages();
        String json = new Gson().toJson(messageList);
        returnResponse(json);
    }
}
