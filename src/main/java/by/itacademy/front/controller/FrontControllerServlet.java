package by.itacademy.front.controller;

import by.itacademy.front.command.FrontCommand;
import by.itacademy.front.command.UnknownCommand;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/front")
public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
//        FrontCommand command = getCommand(request);
//        command.init(getServletContext(), request, response);
//        command.process();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    private FrontCommand getCommand(HttpServletRequest request) throws ServletException, IOException {
        try {
            Class type = Class.forName(String.format(
                    "by.itacademy.front.command.%sCommand",
                    request.getParameter("command")));
            return (FrontCommand) type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }
}
