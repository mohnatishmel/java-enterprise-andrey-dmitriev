package by.itacademy.front.command;


import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.front.mapper.impl.JsonToJavaUserMapper;
import by.itacademy.entities.user.User;
import by.itacademy.service.FacadeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import java.io.IOException;


@Log4j2
public class UpdateUserIsPersonalInformationCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {

        User user = new JsonToJavaUserMapper().map(request);
        facadeService.updateUserPersonalInformation(user);

        returnMessage("User successfully updated", 200);
    }
}
