//package by.itacademy.front.command;
//
//import by.itacademy.exception.ApplicationBasedException;
//import by.itacademy.exception.security.authorization.AuthorizationException;
//import by.itacademy.entities.user.User;
//import by.itacademy.security.service.SecurityContext;
//import by.itacademy.service.FacadeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import javax.servlet.ServletException;
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//public class LoadUsersUnlockedCommand extends LoadUserListCommand{
//
//    private SecurityContext securityContext;
//
//    @Autowired
//    public LoadUsersUnlockedCommand(SecurityContext securityContext) {
//        this.securityContext = securityContext;
//    }
//
//    @Override
//    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {
//        List<User> userList = loadTskListForCurrentUser();
//        List<User> resultLIst = userList.stream()
//                .filter(user -> user.isAccountNonLocked())
//                .filter(user -> ! user.equals(securityContext.getPrincipal()))
//                .collect(Collectors.toList());
//        returnUserList(resultLIst);
//    }
//}
