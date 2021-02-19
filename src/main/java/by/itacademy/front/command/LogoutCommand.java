package by.itacademy.front.command;

import javax.servlet.ServletException;
import java.io.IOException;

public class LogoutCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
            request.getSession().setAttribute("user", null);
            forward("index");
    }
}
