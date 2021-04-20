package by.itacademy.front.command;

import by.itacademy.constant.ApplicationConstant;
import by.itacademy.entities.front.FrontUser;
import by.itacademy.exception.security.authentication.AuthenticationException;
import by.itacademy.front.converter.impl.UserToFrontUserConverter;
import by.itacademy.front.mapper.impl.JsonToJavaAuthenticateTokenMapper;
import by.itacademy.security.model.authentication.AuthenticationToken;
import by.itacademy.entities.user.User;
import by.itacademy.security.service.AuthenticationProvider;
import by.itacademy.security.service.SecurityContext;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;

@Log4j2
public class LoginCommand extends FrontCommand {

    private AuthenticationProvider authenticationProvider;

    {
        authenticationProvider = ApplicationConstant.APPLICATION_CONTEXT.getBean(AuthenticationProvider.class);
    }

    @Override
    public void process() throws ServletException, IOException {

        AuthenticationToken token = new JsonToJavaAuthenticateTokenMapper().map(request);
        User user;
        try {
            user = (User) authenticationProvider.authenticate(token);
            SecurityContext.getInstance().setPrincipal(user);
            user.eraseCredentials();
            FrontUser frontUser = new UserToFrontUserConverter().convert(user);

            SecurityContext.getInstance().setPrincipal(user);
            request.getSession().setAttribute("principle", user);

            String json = new Gson().toJson(frontUser);
            returnResponse(json);

        } catch (AuthenticationException e) {
            log.debug("Can't authenticate user",e);
            String error = "Invalid user data";
            Message message = new Message(error);
            String json = new Gson().toJson(message);
            response.setStatus(401);
            returnResponse(json);
        }
        log.debug(String.format("User with login: '%s' is authenticated", token.getLogin()));
    }
}
