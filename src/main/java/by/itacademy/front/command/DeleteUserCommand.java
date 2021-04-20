package by.itacademy.front.command;


import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.front.mapper.impl.JsonToJavaUserMapper;
import by.itacademy.entities.user.User;
import by.itacademy.service.FacadeService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import java.io.IOException;


@Log4j2
public class DeleteUserCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {

        User user = new JsonToJavaUserMapper().map(request);
        facadeService.deleteUser(user);

        Message message = new Message("User deleted");

        String json = new Gson().toJson(message);
        returnResponse(json);
    }
}
