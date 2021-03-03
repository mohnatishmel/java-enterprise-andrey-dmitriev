package by.itacademy.front.controller;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.front.command.FrontCommand;
import by.itacademy.front.command.UnknownCommand;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "")
public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String queryString = request.getQueryString();
        Map<String, String[]> params  = new HashMap<>();
        if (queryString != null) {
            params = javax.servlet.http.HttpUtils.parseQueryString( queryString );
        }
        process(params,request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        process(params,request,response);
    }

    private void process(Map<String, String[]> params, HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            String commandName = "";

            if (params.containsKey("command")) {
                commandName = params.get("command")[0];
            }

            FrontCommand command = getCommand(commandName);
            command.init(getServletContext(), request, response);

            try {
                command.process();
            } catch (ApplicationBasedException e) {
                e.printStackTrace();
                throw new IOException(e);
            }

        } catch (java.lang.IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private FrontCommand getCommand(String commandName) throws ServletException, IOException {
        try {
            Class type = Class.forName(String.format(
                    "by.itacademy.front.command.%sCommand",
                    commandName));
            return (FrontCommand) type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }

}
