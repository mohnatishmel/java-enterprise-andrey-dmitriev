package by.itacademy.front.command;

import by.itacademy.security.service.SecurityContext;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import java.io.IOException;

public class LogoutCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
            request.getSession().setAttribute("user", null);
        SecurityContext.getInstance().setPrincipal(null);
        String error = "User logged out";
        Message message = new Message(error);
        String json = new Gson().toJson(message);
        returnResponse(json);
    }
}
