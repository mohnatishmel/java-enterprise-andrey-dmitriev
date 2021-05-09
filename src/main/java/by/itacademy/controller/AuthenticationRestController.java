package by.itacademy.controller;

import by.itacademy.entities.front.FrontUser;
import by.itacademy.entities.user.Credential;
import by.itacademy.entities.user.PersonalInformation;
import by.itacademy.entities.user.Role;
import by.itacademy.entities.user.User;
import by.itacademy.exception.InputDataValidationException;
import by.itacademy.exception.security.authentication.AuthenticationException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.front.converter.impl.UserToFrontUserConverter;
import by.itacademy.front.validator.impl.AuthenticationTokenValidator;
import by.itacademy.security.model.authentication.AuthenticationToken;
import by.itacademy.security.model.user.Roles;
import by.itacademy.security.service.AuthenticationProvider;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.service.FacadeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

@RestController
public class AuthenticationRestController {

    private final AuthenticationProvider authenticationProvider;
    private final SecurityContext securityContext;
    private final AuthenticationTokenValidator validator;
    private final FacadeService facadeService;

    @PostMapping("/login")
    public ResponseEntity<FrontUser> login(@RequestBody AuthenticationToken token, HttpSession httpSession)
            throws IOException, AuthenticationException {

        User user = (User) authenticationProvider.authenticate(token);
        securityContext.setPrincipal(user);
        user.eraseCredentials();
        FrontUser frontUser = new UserToFrontUserConverter().convert(user);

        httpSession.setAttribute("principle", user);

        return new ResponseEntity(frontUser, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<FrontUser> register(@RequestBody AuthenticationToken token, HttpSession httpSession)
            throws InputDataValidationException, AuthorizationException {

        List<Role> roleList = new ArrayList<>();

        if (!validator.validate(token)) {
            String error = "Fields can't be empty";
            throw new InputDataValidationException(error);
        } else {
            roleList.add(new Role(Roles.USER_ROLE.name()));

            User user = User.builder()
                    .credential(new Credential(token.getLogin(), token.getPassword()))
                    .personalInformation(new PersonalInformation(0, "", ""))
                    .roles(roleList)
                    .accountNotLocked(true)
                    .build();

            user = facadeService.registerUser(user);

            securityContext.setPrincipal(user);
            httpSession.setAttribute("principle", user);

            FrontUser frontUser = new UserToFrontUserConverter().convert(user);
            return new ResponseEntity(frontUser, HttpStatus.OK);
        }
    }

    @GetMapping("/logout")
    public void logout(HttpSession httpSession) {
        httpSession.setAttribute("user", null);
        securityContext.setPrincipal(null);
    }
}
