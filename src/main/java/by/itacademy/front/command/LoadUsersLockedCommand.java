package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.model.user.User;
import by.itacademy.security.service.SecurityContext;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LoadUsersLockedCommand extends LoadUserListCommand{

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {
        List<User> userList = loadTskListForCurrentUser();
        List<User> resultLIst = userList.stream()
                .filter(user -> user.isAccountNonLocked() == false)
                .filter(user -> ! user.equals(SecurityContext.getInstance().getPrincipal()))
                .collect(Collectors.toList());
        returnUserList(resultLIst);
    }
}
