package by.itacademy.front.command;

import by.itacademy.exception.security.authentication.AuthenticationException;
import by.itacademy.front.mapper.impl.JsonToJavaAuthenticateTokenMapper;
import by.itacademy.model.security.authentication.AuthenticationToken;
import by.itacademy.model.user.User;
import by.itacademy.security.service.AuthenticationProvider;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.service.FacadeService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;

@Log4j2

public class LoginCommand extends FrontCommand {

    private FacadeService facadeService;

    {
        facadeService = FacadeService.getInstance();
    }

    @Override
    public void process() throws ServletException, IOException {

        AuthenticationToken token = new JsonToJavaAuthenticateTokenMapper().map(request);
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
