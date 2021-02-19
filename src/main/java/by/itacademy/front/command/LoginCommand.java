package by.itacademy.front.command;

import by.itacademy.exception.security.authentication.AuthenticationException;
import by.itacademy.model.security.authentication.AuthenticationToken;
import by.itacademy.model.security.user.UserDetails;
import by.itacademy.security.service.AuthenticationProvider;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;

@Log4j2

public class LoginCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AuthenticationToken token = new AuthenticationToken(login, password);

        UserDetails user = null;
        try {
            user = AuthenticationProvider.getInstance().authenticate(token);

        } catch (AuthenticationException e) {
            e.printStackTrace();
            log.debug("Can't authenticate user",Arrays.toString(e.getStackTrace()));

            request.setAttribute("error", "Invalid user data");
            forward("index");
        }

        request.getSession().setAttribute("principle", user);
        forward("user");
        log.debug(String.format("User with login: '%s' is authenticated", login));
    }
}
