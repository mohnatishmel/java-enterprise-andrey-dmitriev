package by.itacademy.front.command;

import by.itacademy.entities.front.FrontUser;
import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.user.User;
import by.itacademy.front.converter.Converter;
import by.itacademy.front.converter.impl.UserToFrontUserConverter;
import by.itacademy.security.service.SecurityContext;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;

@Log4j2

public class GetPrincipalCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {
        User principal = (User) SecurityContext.getInstance().getPrincipal();
        if (principal != null) {
            Converter<User, FrontUser> converter =  new UserToFrontUserConverter();
            FrontUser frontUser = converter.convert(principal);
            String json = new Gson().toJson(frontUser);
            returnResponse(json);
        } else {
            String error = "Invalid user data";
            Message message = new Message(error);
            String json = new Gson().toJson(message);
            response.setStatus(401);
            returnResponse(json);
        }
    }
}
