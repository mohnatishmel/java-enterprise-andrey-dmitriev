package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.model.user.User;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Log4j2

public abstract class LoadUserListCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {
        List<User> userList = loadTskListForCurrentUser();
        returnUserList(userList);
    }

    protected List<User> loadTskListForCurrentUser() throws ApplicationBasedException, AuthorizationException {
        List<User> userList = facadeService.getAllUsers();
        for (User user : userList) {
            user.eraseCredentials();
        }
        return userList;
    }

    protected void returnUserList(List<User> userList) throws IOException {

        String json = new Gson().toJson(userList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
