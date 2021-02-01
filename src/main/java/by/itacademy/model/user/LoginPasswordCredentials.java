package by.itacademy.model.user;

import lombok.Data;

@Data
public class LoginPasswordCredentials {

    private final int id;
    private final String login;
    private final String password;
}
