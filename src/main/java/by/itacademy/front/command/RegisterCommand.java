package by.itacademy.front.command;


import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.front.command.mapper.JsonToJavaAuthenticateTokenMapper;
import by.itacademy.model.security.authentication.AuthenticationToken;
import by.itacademy.model.user.PersonalInformation;
import by.itacademy.persistance.jdbc.dao.user.UserJdbcDao;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.security.user.Roles;
import by.itacademy.model.security.user.UserDetails;
import by.itacademy.model.user.Credential;
import by.itacademy.model.user.Role;
import by.itacademy.model.user.User;
import by.itacademy.security.service.SecurityContext;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

@Log4j2

public class RegisterCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {

        AuthenticationToken token = JsonToJavaAuthenticateTokenMapper.map(request);

        List<Role> roleList = new ArrayList<>();

        try {
            roleList.add(new Role(Roles.USER_ROLE.name()));

            User user = User.builder()
                    .credential(new Credential(token.getLogin(), token.getPassword()))
                    .personalInformation(new PersonalInformation(0, "", ""))
                    .roles(roleList)
                    .build();

            user = service.registerUser(user);

            SecurityContext.getInstance().setPrincipal(user);
            request.getSession().setAttribute("principle", user);

            String json = new Gson().toJson(user);
            returnResponse(json);
        } catch (ApplicationBasedException e) {
            log.debug("Can't register user",Arrays.toString(e.getStackTrace()));
            String error = "Username already exists";
            Message message = new Message(error);
            String json = new Gson().toJson(message);
            response.setStatus(401);
            returnResponse(json);
        }
        log.debug(String.format("User with login: '%s' is authenticated", token.getLogin()));

    }
}
