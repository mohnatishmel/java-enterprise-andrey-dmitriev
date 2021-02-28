package by.itacademy.front.command;

import by.itacademy.security.service.SecurityContext;

import javax.servlet.ServletException;
import java.io.IOException;

public class LogoutCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
            request.getSession().setAttribute("user", null);
        SecurityContext.getInstance().setPrincipal(null);
            forward("login");
    }
}
