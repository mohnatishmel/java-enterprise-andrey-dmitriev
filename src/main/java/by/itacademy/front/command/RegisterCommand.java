package by.itacademy.front.command;


import by.itacademy.persistance.jdbc.dao.user.UserJdbcDao;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.security.user.Roles;
import by.itacademy.model.security.user.UserDetails;
import by.itacademy.model.user.Credential;
import by.itacademy.model.user.Role;
import by.itacademy.model.user.User;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

@Log4j2

public class RegisterCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(Roles.USER_ROLE.name()));

        UserDetails user = User.builder()
                .credential(new Credential(login, password))
                .roles(roleList)
                .build();

        try {
            UserJdbcDao.getInstance().create((User) user);
            log.debug(String.format("User with login: '%s' is registered", login));

            request.getSession().setAttribute("principle", user);
            forward("user");

        } catch (DaoException e) {
            e.printStackTrace();
            log.debug("Can not register user", Arrays.toString(e.getStackTrace()));
            request.setAttribute("error", "User already exist");
            forward("index");
        }

    }
}
