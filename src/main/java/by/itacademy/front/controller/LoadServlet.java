package by.itacademy.front.controller;

import by.itacademy.front.command.FrontCommand;
import by.itacademy.front.command.UnknownCommand;
import by.itacademy.model.task.Task;
import by.itacademy.model.user.User;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.service.Service;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/load")
public class LoadServlet extends HttpServlet {

    private Service service;

    {
        service = Service.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {


        int userId = 1;
        List<Task> taskList = service.getTasksForUser(userId);

        String json = new Gson().toJson(taskList);

//        for (Task task : taskList) {
//            Gson gson = new Gson();
//            JsonElement element = gson.toJsonTree(task);
//            response.getWriter().write(element.toString());
//        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }





}
