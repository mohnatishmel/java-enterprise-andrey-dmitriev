package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.service.FacadeService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {

    protected FacadeService facadeService;
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    {
        facadeService = FacadeService.getInstance();
    }

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
    }

    public abstract void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException;

    protected void forward(String target) throws ServletException, IOException {
        target = String.format("/WEB-INF/jsp/%s.jsp", target);
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }

    protected void returnMessage(String messageBody, int responseStatus) throws IOException {
        Message message = new Message(messageBody);
        String json = new Gson().toJson(message);
        response.setStatus(responseStatus);
        returnResponse(json);
    }

    protected void returnResponse(String json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @AllArgsConstructor
    protected class Message {
        private String message;
    }
}
