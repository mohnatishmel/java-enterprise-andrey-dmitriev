package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.entities.user.User;
import by.itacademy.security.service.SecurityContext;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LoadUsersUnlockedCommand extends LoadUserListCommand{

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {
        List<User> userList = loadTskListForCurrentUser();
        List<User> resultLIst = userList.stream()
                .filter(user -> user.isAccountNonLocked())
                .filter(user -> ! user.equals(SecurityContext.getInstance().getPrincipal()))
                .collect(Collectors.toList());
        returnUserList(resultLIst);
    }
}
