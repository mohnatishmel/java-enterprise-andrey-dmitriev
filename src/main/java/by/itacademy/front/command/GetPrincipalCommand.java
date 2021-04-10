package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.user.User;
import by.itacademy.security.service.SecurityContext;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;

@Log4j2

public class GetPrincipalCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {
        User principal = (User) SecurityContext.getInstance().getPrincipal();
        if (principal != null) {
        String json = new Gson().toJson(principal);
        returnResponse(json);
        } else {
            String error = "Invalid user data";
            Message message = new Message(error);
            String json = new Gson().toJson(message);
            response.setStatus(401);
            returnResponse(json);
        }
    }
}
