package by.itacademy.front.command;


import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.front.mapper.impl.JsonToJavaAuthenticateTokenMapper;
import by.itacademy.front.validator.impl.AuthenticationTokenValidator;
import by.itacademy.security.model.authentication.AuthenticationToken;
import by.itacademy.entities.user.PersonalInformation;
import by.itacademy.security.model.user.Roles;
import by.itacademy.entities.user.Credential;
import by.itacademy.entities.user.Role;
import by.itacademy.entities.user.User;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.service.FacadeService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

@Log4j2
public class RegisterCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {

        AuthenticationToken token = new JsonToJavaAuthenticateTokenMapper().map(request);

        List<Role> roleList = new ArrayList<>();

        AuthenticationTokenValidator validator = new AuthenticationTokenValidator();
        if (!validator.validate(token)) {
            String error = "Fields can't be empty";
            returnMessage(error, 401);
        } else {
            try {
                roleList.add(new Role(Roles.USER_ROLE.name()));

                User user = User.builder()
                        .credential(new Credential(token.getLogin(), token.getPassword()))
                        .personalInformation(new PersonalInformation(0, "", ""))
                        .roles(roleList)
                        .accountNotLocked(true)
                        .build();

                user = facadeService.registerUser(user);

                SecurityContext.getInstance().setPrincipal(user);
                request.getSession().setAttribute("principle", user);

                String json = new Gson().toJson(user);
                returnResponse(json);
            } catch (ApplicationBasedException | AuthorizationException e) {
                log.debug("Can't register user",e);
                String error = "Username already exists";
                returnMessage(error, 401);
            }
            log.debug(String.format("User with login: '%s' is authenticated", token.getLogin()));

        }
    }
}
