package by.itacademy.front.command;

import by.itacademy.exception.security.authentication.AuthenticationException;
import by.itacademy.front.command.mapper.JsonToJavaAuthenticateTokenMapper;
import by.itacademy.model.security.authentication.AuthenticationToken;
import by.itacademy.model.security.user.UserDetails;
import by.itacademy.model.task.Task;
import by.itacademy.model.user.User;
import by.itacademy.persistance.jdbc.dao.task.TaskJdbcDao;
import by.itacademy.security.service.AuthenticationProvider;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.service.Service;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Log4j2

public class LoginCommand extends FrontCommand {

    private Service service;

    {
        service = Service.getInstance();
    }

    @Override
    public void process() throws ServletException, IOException {

        AuthenticationToken token = JsonToJavaAuthenticateTokenMapper.map(request);
        User user = null;
        try {
            user = (User) AuthenticationProvider.getInstance().authenticate(token);
            user.eraseCredentials();

            SecurityContext.getInstance().setPrincipal(user);
            request.getSession().setAttribute("principle", user);

            String json = new Gson().toJson(user);
            returnResponse(json);

        } catch (AuthenticationException e) {
            log.debug("Can't authenticate user",Arrays.toString(e.getStackTrace()));
            String error = "Invalid user data";
            Message message = new Message(error);
            String json = new Gson().toJson(message);
            response.setStatus(401);
            returnResponse(json);
        }
        log.debug(String.format("User with login: '%s' is authenticated", token.getLogin()));
    }
}
