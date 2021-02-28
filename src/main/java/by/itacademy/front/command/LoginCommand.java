package by.itacademy.front.command;

import by.itacademy.exception.security.authentication.AuthenticationException;
import by.itacademy.model.security.authentication.AuthenticationToken;
import by.itacademy.model.security.user.UserDetails;
import by.itacademy.model.user.User;
import by.itacademy.persistance.jdbc.dao.task.TaskJdbcDao;
import by.itacademy.security.service.AuthenticationProvider;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.service.Service;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;

@Log4j2

public class LoginCommand extends FrontCommand {

    private Service service;

    {
        service = Service.getInstance();
    }

    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AuthenticationToken token = new AuthenticationToken(login, password);

        User user = null;
        try {
            user = (User) AuthenticationProvider.getInstance().authenticate(token);

        } catch (AuthenticationException e) {
            e.printStackTrace();
            log.debug("Can't authenticate user",Arrays.toString(e.getStackTrace()));

            request.setAttribute("error", "Invalid user data");
            forward("login");
        }

        SecurityContext.getInstance().setPrincipal(user);
        request.getSession().setAttribute("principle", user);

        forward("main");
        log.debug(String.format("User with login: '%s' is authenticated", login));
    }
}
