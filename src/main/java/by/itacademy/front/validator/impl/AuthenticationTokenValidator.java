package by.itacademy.front.validator.impl;

import by.itacademy.front.validator.Validator;
import by.itacademy.security.model.authentication.AuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationTokenValidator implements Validator<AuthenticationToken> {

    @Override
    public boolean validate(AuthenticationToken token) {
        boolean result = false;
        int i = token.getLogin().length();
        int t = token.getPassword().length();
        if (token.getLogin().length() != 0 & token.getPassword().length() != 0) {
            result = true;
        }
        return result;
    }
}
