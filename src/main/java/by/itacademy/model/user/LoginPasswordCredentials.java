package by.itacademy.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginPasswordCredentials {

    private final String login;
    private final String password;
}
